package com.celestial.pingme.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.celestial.pingme.services.PingService;


@RestController	
@RequestMapping("/api")
public class PingController 
{
	final private	String	PINGME_ENDPOINT = "PINGME_ENDPOINT"; 
	/* Kubernetes will create a number of environment variables for you so you can located other PODs
	 * If you a Service of type NodePort like this
	 * apiVersion: v1
	 * kind: Service
	 * metadata:
	 *	  name: remote-pingme-server-ep
	 *	  namespace: default
	 * spec:
	 *	  type: NodePort
	 *	  selector:
	 *	    service_id: remote-pingme-server
	 *	  ports:
	 *	  - nodePort: 30082
	 *	    port: 8080
	 *	    targetPort: 8080
	 *
	 * from the metadata.name remote-pingme-server-ep, K8 will create two an environment variables
	 * REMOTE_PINGME_SERVER_EP_SERVICE_HOST with the IP address of the POD's cluster
	 * and
	 * REMOTE_PINGME_SERVER_EP_SERVICE_PORT with the port that POD is exposed on on that cluster
	 * 
	 * With this information we can automate the linking between the PODs
	 */
//	final private	String	PINGME_K8_ENDPOINT = "REMOTE_PINGME_SERVER_EP_SERVICE_HOST"; 
//	final private	String	PINGME_K8_PORT = "REMOTE_PINGME_SERVER_EP_SERVICE_PORT"; 
	private final String TIME_API = "/api/time";
	private	PingService thePingService;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	public	PingController( PingService ps )
	{
		thePingService = ps;
	}
	
	@GetMapping("/status")
	public	String getStatus()
	{
		return "Healthy";
	}
	
	@GetMapping("/time")
	public	String getTime()
	{
		String theTime = thePingService.getTime();
		
		return theTime;
	}

	@GetMapping("/timefromhelper")
	public	String getTimex()
	{
		LocalTime time = LocalTime.now();
		String theTime = "[NOT SET]";
		String endpoint = System.getenv().getOrDefault(PINGME_ENDPOINT, null);

		// Grads don't get this version, they must pass the environment variable
//		if( endpoint == null )
//		{
//			endpoint = System.getenv().getOrDefault(PINGME_K8_ENDPOINT, null);
//			if( endpoint != null )
//			{
//				String port = System.getenv().getOrDefault(PINGME_K8_PORT, "8080");
//				endpoint = "http://" + endpoint + ":" + port;
//			}
//		}
				
		if(endpoint != null )
		{
			theTime = webClientBuilder.build()
						.get()
						.uri(endpoint + TIME_API)
						.retrieve()
						.bodyToMono(String.class)
						.block();
		}
		else
			theTime = "**" + thePingService.getTime();
		
		return theTime;
	}
}
