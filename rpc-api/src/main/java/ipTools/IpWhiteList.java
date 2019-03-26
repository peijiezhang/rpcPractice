package ipTools;

import java.util.List;


public class IpWhiteList {

    private boolean isEnabled; // 是否启用白名单
    private List<String> allowIps; // 允许的白名单列表
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<String> getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(List<String> allowIps) {
        this.allowIps = allowIps;
    }
}
