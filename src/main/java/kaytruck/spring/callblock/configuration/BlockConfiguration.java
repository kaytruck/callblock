package kaytruck.spring.callblock.configuration;

import java.util.ArrayList;
import java.util.List;

public class BlockConfiguration {

    private List<String> targetNames = new ArrayList<>();

    public BlockConfiguration() {
    }

    public List<String> getTargetNames() {
        return targetNames;
    }

    public void setTargetNames(List<String> targetNames) {
        this.targetNames = targetNames;
    }

    
}