/**
 * 
 */
package com.bhaskar.aop.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

/**
 * @author Bhaskar
 *
 * 
 */
@Component
public class TrafficFortuneService {
	
	public String getFortune()
	{
		try {
			
			TimeUnit.SECONDS.sleep(5);
	}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		return "Expect heavy traffic this morning";

}

	/**
	 * @param tripWire
	 * @return
	 */
	public String getFortune(boolean tripWire) {
		// TODO Auto-generated method stub
		if(tripWire) {
			throw new RuntimeException("Major accident Highway is closed");
		}
		return getFortune();
	}
}

