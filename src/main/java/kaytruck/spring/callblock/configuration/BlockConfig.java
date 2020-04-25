package kaytruck.spring.callblock.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "block-config")
public class BlockConfig {

    private boolean includeMethod;
    private List<String> controlKeys;
    private Map<String, BlockStatusCode> blockStatus;;

    @PostConstruct
    public void postConstruct() {
        controlKeys.forEach(System.out::println);
        blockStatus = controlKeys.stream()
            .filter(s -> s.split("#").length == 2)
            .collect(Collectors.toMap(
                s -> s.split("#")[0], 
                s -> BlockStatusCode.getCode(s.split("#")[1])
                )
            );
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