package kaytruck.spring.callblock.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
// @ConfigurationProperties(prefix = "block-config")
public class BlockConfig {

    private boolean includeMethod;
    private List<String> controlKeys;
    private Map<String, BlockStatusCode> blockStatus;;

    @PostConstruct
    public void postConstruct() {
        // TODO 設定ファイルパスはXMLファイルで定義する
        // loadFile("c:/tmp/blockConfig.yaml");
        // TODO 閉塞ステータスの動的更新に対応
        loadFile("blockConfig.yaml");
        controlKeys.forEach(System.out::println);
        blockStatus = controlKeys.stream()
            .filter(s -> s.split("#").length == 2)
            .collect(Collectors.toMap(
                s -> s.split("#")[0], 
                s -> BlockStatusCode.getCode(s.split("#")[1])
                )
            );
    }

    private void loadFile(String fileName) {
        Yaml yaml = new Yaml();
        try (FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
        ) {
            // System.out.println("@@@@loadMap");
            BlockConfig blockConfig = yaml.loadAs(br, BlockConfig.class);
            this.includeMethod = blockConfig.isIncludeMethod();
            this.controlKeys = blockConfig.getControlKeys();
            // System.out.println("@@@@" + blockConfig.getControlKeys());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getControlKeys() {
        return controlKeys;
    }

    public void setControlKeys(List<String> controlKeys) {
        this.controlKeys = controlKeys;
    }

    public Map<String, BlockStatusCode> getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(Map<String, BlockStatusCode> blockStatus) {
        this.blockStatus = blockStatus;
    }

    public boolean isIncludeMethod() {
        return includeMethod;
    }

    public void setIncludeMethod(boolean includeMethod) {
        this.includeMethod = includeMethod;
    }

}