package customers_web_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import customer_core.service.CustomerDataService;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;
import customers_core.db.CustomerStatusDB;
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

		List<CustomerCommentJSON> commentsJSON = new ArrayList<CustomerCommentJSON>();

		for (CommentDB comment : customerComments)
			commentsJSON.add(new CustomerCommentJSON(comment.getId(), comment.getCommentText(), comment.getCreated()));
		return commentsJSON;
	}

	@GetMapping(value = "/customers/statuses", produces = { "application/json" })
	@ResponseBody
	public List<CustomerStatusDB> getCustomerStatuses() {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		List<CustomerStatusDB> customerStatuses = customerDataService.getCustomerStatuses();

		return customerStatuses;
	}

	// TODO: return new comment id if success or API error in case of error
	@PostMapping(value = "/customers/{customerId}/comments")
	public void saveCustomerComment(@RequestBody CommentDB newComment, @PathVariable int customerId) {
		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		
		// check if customer exists
		CustomerDB customer = customerDataService.getCustomerById(customerId);
		
		if (customer == null) 
			return; //TODO: send invalid paramter
		newComment.setCustomer(customer);
		customerDataService.saveComment(newComment);

	}
}
