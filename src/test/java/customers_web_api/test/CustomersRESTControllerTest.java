package customers_web_api.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CustomersRESTControllerTest {
	private static final String DEV_SERVER_URL = "http://localhost:8080/customers-web-api";
	private static final String CUSTOMERS_URI = "/customers/";
	private static final String CUSTOMER_COMMENTS_URI = "/comments/";
	protected Logger logger = LogManager.getLogger(getClass());

	@Test
	public void getCustomerTest() throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();

		int customerId = 22;
		HttpResponse response = client.execute(new HttpGet(DEV_SERVER_URL + CUSTOMERS_URI + customerId));
		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals((HttpStatus.SC_OK), statusCode);
		String responseString = EntityUtils.toString(response.getEntity());
		logger.info("response string " + responseString);
	}

	@Test
	public void getAllCustomersTest() throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();

		HttpResponse response = client.execute(new HttpGet(DEV_SERVER_URL + CUSTOMERS_URI));
		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals((HttpStatus.SC_OK), statusCode);
		String responseString = EntityUtils.toString(response.getEntity());
		logger.info("response string " + responseString);
	}

	@Test
	public void getCustomerCommentsTest() throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();
		int customerId = 23;

		String resourceURI = DEV_SERVER_URL + CUSTOMERS_URI + customerId + CUSTOMER_COMMENTS_URI;
		logger.info("get customer comments uri " + resourceURI);
		HttpResponse response = client
				.execute(new HttpGet(resourceURI));
		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals((HttpStatus.SC_OK), statusCode);
		String responseString = EntityUtils.toString(response.getEntity());
		logger.info("response string " + responseString);
	}
}
