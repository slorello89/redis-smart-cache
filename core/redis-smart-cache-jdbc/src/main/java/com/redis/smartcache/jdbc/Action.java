package com.redis.smartcache.jdbc;

import java.time.Duration;

public class Action {

	public static final Duration TTL_NO_CACHING = Duration.ZERO;
	public static final long TTL_NO_EXPIRATION = -1;

	private Duration ttl = TTL_NO_CACHING;

	public Duration getTtl() {
		return ttl;
	}

	public void setTtl(Duration ttl) {
		if (ttl == null || ttl.isNegative()) {
			throw new IllegalArgumentException("TTL duration must be greater than 0");
		}
		this.ttl = ttl;
	}

	public boolean isCaching() {
		return !TTL_NO_CACHING.equals(ttl);
	}

}
