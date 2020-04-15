package works.weave.socks.queuemaster.loadtest;

import com.neotys.neoload.model.repository.*;
import com.neotys.neoload.model.v3.project.server.Server;
import com.neotys.neoload.model.v3.project.userpath.ImmutableRequest;
import com.neotys.neoload.model.v3.project.userpath.ThinkTime;
import com.neotys.neoload.model.v3.project.variable.Variable;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;

import java.util.Optional;

import static java.util.Collections.emptyList;

public class BasicUserPath extends BaseNeoLoadUserPath {
    public BasicUserPath(BaseNeoLoadDesign design) {
        super(design);
    }

    @Override
    public com.neotys.neoload.model.v3.project.userpath.UserPath createVirtualUser(BaseNeoLoadDesign baseNeoLoadDesign) {
        final Server server = baseNeoLoadDesign.getServerByName("host");
        final Variable constantpath= baseNeoLoadDesign.getVariableByName("basicPath");

        final ImmutableRequest getRequest = getBuilder(server, variabilize(constantpath), emptyList(),emptyList(),Optional.empty()).build();

        final ThinkTime delay = thinkTime(250);
        final com.neotys.neoload.model.v3.project.userpath.Container actionsContainer = actionsContainerBuilder()
                .addSteps(container("BasicCheck", Optional.empty(), getRequest, delay))
                .build();

        return userPathBuilder("BasicCheckTesting")
                .actions(actionsContainer)
                .build();


    }
}
