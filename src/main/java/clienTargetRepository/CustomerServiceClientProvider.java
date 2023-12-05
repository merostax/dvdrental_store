package clienTargetRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

@ApplicationScoped
public class CustomerServiceClientProvider {
    private Client client;
    private WebTarget customerServiceTarget;

    @Inject
    public CustomerServiceClientProvider() {
        client = ClientBuilder.newClient();
        this.customerServiceTarget = initializeCustomerServiceTarget();
    }

    private WebTarget initializeCustomerServiceTarget() {
        String customerServiceUri = System.getProperty("customer.service.uri");

        if (customerServiceUri == null || customerServiceUri.isEmpty()) {
            System.out.println("Warning: Customer service URI not set. "
                    + "Set it using system property -Dcustomer.service.uri=http://your-customer-service-uri");
        }

        return client.target(customerServiceUri);
    }
    public WebTarget getCustomerServiceTarget() {
        return customerServiceTarget;
    }

}
