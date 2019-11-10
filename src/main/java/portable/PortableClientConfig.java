package portable;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("portable")
public class PortableClientConfig {

    private String topic;

    String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
