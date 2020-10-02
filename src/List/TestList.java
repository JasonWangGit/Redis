package List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

public class TestList {
	private static final String KEY = "employee_list";
	
	public static void main(String[] args) {
		// open Redis connection
		Jedis jedis = new Jedis("localhost", 6379);
		
		// LPUSH
		jedis.lpush(KEY, "Steven");
		jedis.lpush(KEY, "Neena");
		jedis.lpush(KEY, "Lex");
		jedis.lpush(KEY, "Alexander");
		jedis.lpush(KEY, "Bruce");
		
		// BLPOP
		System.out.println(jedis.blpop(10, KEY));
		
		// BRPOP
		System.out.println(jedis.brpop(10, KEY));
		
		// BRPOPLPUSH
		jedis.brpoplpush(KEY, "temp_list", 0);
				
		// LINDEX
		System.out.println(jedis.lindex(KEY, 0));
		
		// LINSERT
		jedis.linsert(KEY, ListPosition.BEFORE, "Lex", "Daniel");
		
		// LLEN
		System.out.println(jedis.llen(KEY));
		
		// LPOP
		System.out.println(jedis.lpop(KEY));
		
		// LPUSH: at the beginning
		
		// LPUSHX
		jedis.lpushx(KEY, "David");
		
		// LRANGE
		System.out.println(jedis.lrange(KEY, 0, 2));
		
		// LREM
		jedis.lrem(KEY, 1, "Lex");
		
		// LSET
		jedis.lset(KEY, 0, "Valli");
		
		// LTRIM
		jedis.ltrim(KEY, 0, 4);
		
		// RPOP
		System.out.println(jedis.rpop(KEY));
		
		// RPUSH
		jedis.rpush(KEY, "Diana");
		
		// RPUSHX
		jedis.rpushx(KEY, "Nancy");
		
		// close Redis connection
		jedis.close();
	}
}
