package org.wso2.carbon.andes.mqtt.internal;

import org.dna.mqtt.moquette.server.Server;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.kernel.CarbonRuntime;

import java.util.logging.Logger;

/**
 * Declarative service component for MQTT.
 * This handles initialization of the transport
 */
@Component(
        name = "org.wso2.carbon.andes.mqtt.internal.MqttTransportServiceComponent",
        immediate = true
)
public class MqttTransportServiceComponent {

    Logger logger = Logger.getLogger(MqttTransportServiceComponent.class.getName());
    private ServiceRegistration mqttTransportService;
    //TODO we need to change this to get from the configuration
    //This will be a temporary measure
    private static final int MQTT_PORT = 1883;
    //The running MQTT server instance
    private Server mqttServer = null;

    /**
     * This is the activation method of MqttTransportServiceComponent. This will be called when its references are
     * satisfied.
     *
     * @param bundleContext the bundle context instance of this bundle.
     * @throws Exception this will be thrown if an issue occurs while executing the activate method
     */
    @Activate
    protected void start(BundleContext bundleContext) throws Exception {
        logger.info("MqttTransportServiceComponent started in disabled mode");
        //TODO this is a bad way of starting the service, without registering a service
        //This is temporary
        startMQTTBroker(Server.DEFAULT_MQTT_PORT);
//        mqttServer = new Server();
//        mqttServer.startServer(MQTT_PORT);
    }

    protected void startMQTTBroker(int port) throws Exception {
        Server server = new Server();
        server.startServer(port);
    }

    /**
     * This is the deactivation method of MqttTransportServiceComponent. This will be called when this component
     * is being stopped or references are satisfied during runtime.
     *
     * @throws Exception this will be thrown if an issue occurs while executing the de-activate method
     */
    @Deactivate
    protected void stop() throws Exception {
        logger.info("MqttTransportServiceComponent deactivated");

        //We stop the server when the bundle is deactivated
        if (null != mqttServer) {
            mqttServer.stopServer();
        }

        // Unregister Greeter OSGi service
        mqttTransportService.unregister();
    }

    /**
     * This bind method will be called when CarbonRuntime OSGi service is registered.
     *
     * @param carbonRuntime The CarbonRuntime instance registered by Carbon Kernel as an OSGi service
     */
    @Reference(
            name = "carbon.runtime.service",
            service = CarbonRuntime.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetCarbonRuntime"
    )
    protected void setCarbonRuntime(CarbonRuntime carbonRuntime) {
        DataHolder.getInstance().setCarbonRuntime(carbonRuntime);
    }

    /**
     * This is the unbind method which gets called at the un-registration of CarbonRuntime OSGi service.
     *
     * @param carbonRuntime The CarbonRuntime instance registered by Carbon Kernel as an OSGi service
     */
    protected void unsetCarbonRuntime(CarbonRuntime carbonRuntime) {
        DataHolder.getInstance().setCarbonRuntime(null);
    }

}
