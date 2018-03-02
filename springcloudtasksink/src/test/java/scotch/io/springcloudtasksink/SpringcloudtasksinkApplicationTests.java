package scotch.io.springcloudtasksink;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringcloudtasksinkApplication.class)
public class SpringcloudtasksinkApplicationTests {

    @Autowired
    ApplicationContext context;

    @Autowired
    private Sink sink;
    

	@Test
	public void contextLoads() {
	}
	
    @Test
    public void testTaskLaunch() throws IOException {

        TaskLauncher taskLauncher =
            context.getBean(TaskLauncher.class);

        Map<String, String> prop = new HashMap<String, String>();
        prop.put("server.port", "8080");
        TaskLaunchRequest request = new TaskLaunchRequest(
            "maven://scotch.io:springcloudtaskbatch:jar:0.0.1-SNAPSHOT", null,
            prop,
            null, null);
        GenericMessage<TaskLaunchRequest> message = new GenericMessage<TaskLaunchRequest>(
            request);
        this.sink.input().send(message);

        ArgumentCaptor<AppDeploymentRequest> deploymentRequest = ArgumentCaptor
            .forClass(AppDeploymentRequest.class);

        verify(taskLauncher).launch(
            deploymentRequest.capture());

        AppDeploymentRequest actualRequest = deploymentRequest
            .getValue();

        // Verifying the co-ordinate of launched Task here.
        assertTrue(actualRequest.getCommandlineArguments()
            .isEmpty());
        assertEquals("8080", actualRequest.getDefinition()
            .getProperties().get("server.port"));
        assertTrue(actualRequest
            .getResource()
            .toString()
            .contains(
                "scotch.io:springcloudtaskbatch:jar:0.0.1-SNAPSHOT"));
    }
	
    

}
