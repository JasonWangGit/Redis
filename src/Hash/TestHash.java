package Hash;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;

public class TestHash {
	private static final String KEY = "employee";
	private static final String EMPLOYEE_ID = "employee_id";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String SALARY = "salary";
	private static final String DEPARTMENT_ID = "department_id";
	
	public static void main(String[] args) {
		// open Redis connection
		Jedis jedis = new Jedis("localhost", 6379);
		
		// data container
		Map<String, String> employee = new HashMap<>();
		employee.put(EMPLOYEE_ID, "100");
		employee.put(FIRST_NAME, "Steven");
		employee.put(LAST_NAME, "King");
		employee.put(SALARY, "24000.0");
		employee.put(DEPARTMENT_ID, "90");
		
		// HMSET
		jedis.hmset(KEY, employee);
		
		// HDEL
		jedis.hdel(KEY, EMPLOYEE_ID);
		
		// HEXISTS
		System.out.println(jedis.hexists(KEY, EMPLOYEE_ID));
		
		// HGET
		System.out.println(jedis.hget(KEY, FIRST_NAME));
		
		// HGETALL
		System.out.println(jedis.hgetAll(KEY));
		
		// HINCRBY
		jedis.hincrBy(KEY, DEPARTMENT_ID, 9);
		System.out.println(jedis.hgetAll(KEY));
		
		// HINCRYBYFLOAT
		jedis.hincrByFloat(KEY, SALARY, 9.9);
		System.out.println(jedis.hgetAll(KEY));
		
		// HKEYS
		System.out.println(jedis.hkeys(KEY));
		
		// HELN
		System.out.println(jedis.hlen(KEY));
		
		// HMGET
		System.out.println(jedis.hmget(KEY, FIRST_NAME, LAST_NAME));
		
		// HMSET: at the beginning
		
		// HSETNX
		jedis.hsetnx(KEY, FIRST_NAME, "Conner");
		System.out.println(jedis.hgetAll(KEY));
		
		// HVALS
		System.out.println(jedis.hvals(KEY));
		
		// HSCAN
		ScanParams scanParams = new ScanParams().match("*name");
		System.out.println(jedis.hscan(KEY, String.valueOf(0), scanParams).getResult());
		
		// close Redis connection
		jedis.close();
	}
}
