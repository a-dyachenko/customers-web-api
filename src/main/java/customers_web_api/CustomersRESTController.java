package customers_web_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import customer_core.service.CustomerDataService;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;
import customers_web_api.data.CustomerCommentJSON;

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

	@GetMapping(value = "/customers", produces = { "application/json" })
	@ResponseBody
	public List<CustomerDB> getAllCustomers() {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		List<CustomerDB> customers = customerDataService.getAllCustomers();

		return customers;
	}

	@GetMapping(value = "/customers/{customerId}/comments", produces = { "application/json" })
	@ResponseBody
	public List<CustomerCommentJSON> getCustomerComments(@PathVariable int customerId) {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		List<CommentDB> customerComments = customerDataService.getCustomerComments(customerId);

		System.out.println("comments db size " + customerComments.size());
		List<CustomerCommentJSON> commentsJSON = new ArrayList<CustomerCommentJSON>();

		for (CommentDB comment : customerComments)
			commentsJSON.add(new CustomerCommentJSON(comment.getId(), comment.getCommentText(), comment.getCreated()));
		System.out.println("comments json size " + commentsJSON.size());
		return commentsJSON;
	}

}
