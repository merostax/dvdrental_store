package clienTargetRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

@ApplicationScoped
public class CustomerServiceClientProvider {
    private Client client;
    private WebTarget customerServiceTarget;
    public CustomerServiceClientProvider() {
        client = ClientBuilder.newClient();
        this.customerServiceTarget = client.target("http://localhost:8083/");
    }

    public WebTarget getCustomerServiceTarget() {
        return customerServiceTarget;
    }
}
