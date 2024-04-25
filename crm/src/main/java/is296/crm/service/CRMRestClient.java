package is296.crm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CRMRestClient {
	
	public String getStarWarsAPI() {
		RestClient restClient = RestClient.create();

		String result = restClient.get()
		  .uri("https://swapi.dev/api/starships/9/")
		  .retrieve()
		  .body(String.class);
		System.out.println(result);
		return result;
	}

}
