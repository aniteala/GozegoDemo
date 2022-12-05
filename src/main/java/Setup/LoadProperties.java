package Setup;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    Properties properties = new Properties();
    public void loadProperties() {
        try {
            properties.load(LoadProperties.class.getClassLoader().getResourceAsStream("Configuration.properties"));
        } catch (IOException e) {}
    }

    public String getConfigProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }

}
