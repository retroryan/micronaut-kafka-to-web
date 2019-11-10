package portable;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("portable")
public class PortableClientConfig {

    private String topic;

    private String revision;

    String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        //Why does Micronaut include the : in the string
        // i.e. the revision string comes in as 32:
        this.revision = revision.substring(0, revision.length()-1);
    }
}
