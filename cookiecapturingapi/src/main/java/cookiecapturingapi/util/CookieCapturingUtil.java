package cookiecapturingapi.util;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.data.authentication.UserCredentials;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


public class CookieCapturingUtil {
	
	
	
	public static void main(String[] args) {
		
		
		
		
		//final String url = "http://codeflex.co:8080/rest/Management/login";
		final String url1 = "http://google.co.in";

		RestTemplate template = new RestTemplate();
		UserCredentials cred = new UserCredentials(null,null);
		HttpEntity<UserCredentials> request = new HttpEntity<UserCredentials>(cred);       
		HttpEntity<String> response = template.exchange(url1, HttpMethod.GET, request, String.class);
		HttpHeaders headers = response.getHeaders();
		System.out.println(headers);
		
		String set_cookie = headers.getFirst(HttpHeaders.COOKIE);
		System.out.println(set_cookie);
		
		
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);
		
		//creates url for the given string 
		URL url = null;
		try {
			url = new URL("http://google.co.in");
			//open's a connection with the url specified and returns URLConnection object
			URLConnection  urlConnection = url.openConnection(); 
			// get's the contents from this url specifies
			urlConnection.getContent(); 
			
			
			System.out.println(urlConnection.getHeaderFields());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		//returns the cookie store(bunch of cookies)
		CookieStore cookieStore = cookieManager.getCookieStore();
		
		
		//getting cookies which returns in the form of List of type HttpCookie
		List<HttpCookie> listOfcookies = cookieStore.getCookies();
		
		for(HttpCookie httpCookie: listOfcookies){
			
			
			
			
			
			System.out.println("Cookie Name : "+httpCookie.getName()+" Cookie Value : "+httpCookie.getValue());
		}

	}

}
