package kaytruck.spring.callblock.aop;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import kaytruck.spring.callblock.configuration.BlockConfig;
import kaytruck.spring.callblock.configuration.BlockStatusCode;
import kaytruck.spring.callblock.exception.BlockException;
import kaytruck.spring.callblock.exception.SystemException;

public class BlockAdvice {

    @Autowired
    private BlockConfig blockConfig;

    private Map<String, BlockStatusCode> statusMap;

    @PostConstruct
    public void postConstruct() {
        statusMap = blockConfig.getBlockStatus();
    }
    
    public Object aroundInvoke(ProceedingJoinPoint jp) {
        // 実行しようとしているクラス名を取得する
        Signature signature = jp.getSignature();
        String invoke = signature.getDeclaringTypeName();
        if (blockConfig.isIncludeMethod()) {
            // 実行対象の判定にメソッド名も含める
            invoke = invoke + "." + signature.getName();
        }

        // 閉塞ステータスの確認
        BlockStatusCode blockStatus = statusMap.get(invoke);
        if (BlockStatusCode.CLOSE.equals(blockStatus)) {
            throw new BlockException("閉塞中");
        }

        try {
            // 対象の実行
            Object result = jp.proceed();
            return result;
        } catch (Throwable e) {
            throw new SystemException("システム障害");
        }
    }

}