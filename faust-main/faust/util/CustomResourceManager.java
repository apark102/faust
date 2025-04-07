package faust.util;
import java.util.Map;
import java.util.HashMap;
public class CustomResourceManager {
    private static final int NUM_RESOURCES = 7;

    public enum ResourceType {
        PRIDE, GLOOM, LUST, WRATH, GLUTTONY, SLOTH, ENVY
    }

    private static final Map<ResourceType, Integer> resources = new HashMap<>();

    static {
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0);
        }
    }

    public static void gainResource(ResourceType type, int amount) {
        resources.put(type, resources.get(type) + amount);
    }

    public static boolean spendResource(ResourceType type, int amount) {
        if (resources.get(type) >= amount) {
            resources.put(type, resources.get(type) - amount);
            return true;
        }
        return false;
    }

    public static int getResourceAmount(ResourceType type) {
        return resources.get(type);
    }

    public static void resetResources() {
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0);
        }
    }
}
