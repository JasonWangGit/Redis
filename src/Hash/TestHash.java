package Hash;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class TestHash {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		Map<String, String> employee = new HashMap<>();
		employee.put("employee_id", "100");
		employee.put("first_name", "Steven");
		employee.put("last_name", "King");
		
		// HMSET method
		jedis.hmset("employee", employee);
		
		// HGETALL method
		Map<String, String> employeeReturn = new HashMap<>();
		employeeReturn.putAll(jedis.hgetAll("employee"));
		System.out.println(employeeReturn);
		
		jedis.close();
	}
}
