package portable;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


@Controller("/")
public class Application implements ApplicationEventListener<ServerStartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private final PortableClientConfig portableClientConfig;

    public Application(PortableClientConfig portableClientConfig) {
        this.portableClientConfig = portableClientConfig;
    }

    @Inject
    private WsServer wsServer;


    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    @View("index")
    @Get("/")
    public HttpResponse<RevisionInfo> index() {
        String revision = portableClientConfig.getRevision();
        LOG.info("Revision: " + revision);
        return HttpResponse.ok(new RevisionInfo(revision));
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {

    }

    @EventListener
    public void onStartup(ServerStartupEvent event) {
        LOG.info("Connecting with ws client config = " + portableClientConfig);
        LOG.info("topics = " + portableClientConfig.getTopic());
        LOG.info("Revision: " + portableClientConfig.getRevision());

    }
}


