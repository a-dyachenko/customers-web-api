package customers_web_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import customer_core.service.CustomerDataService;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.db.CustomerDB;

@RestController
public class CustomersRESTController {

	@GetMapping(value = "/customers/{customerId}", produces = { "application/json" })
	@ResponseBody
	public CustomerDB getCustomer(@PathVariable int customerId) {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		CustomerDB customer = customerDataService.getCustomerById(customerId);

		return customer;
	}

}
