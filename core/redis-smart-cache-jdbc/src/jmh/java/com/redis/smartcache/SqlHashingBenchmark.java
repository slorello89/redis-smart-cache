package com.redis.smartcache;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.MurmurHash3;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import com.redis.smartcache.core.util.CRC32HashingFunction;

public class SqlHashingBenchmark {

	private static final String SQL = "SELECT orders.orderNumber, orders.orderDate, orders.requiredDate, orders.shippedDate, orders.status, orders.customerNumber, customers.customerName, orderdetails.productCode, products.productName, orderdetails.quantityOrdered FROM orders JOIN customers ON orders.customerNumber = customers.customerNumber JOIN orderdetails ON orders.orderNumber = orderdetails.orderNumber JOIN products ON orderdetails.productCode = products.productCode WHERE orders.orderNumber = ?";
	private static final CRC32HashingFunction CRC32 = new CRC32HashingFunction();

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	public void crc32() {
		CRC32.hash(SQL);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	public void murmur3() {
		MurmurHash3.hash32x86(SQL.getBytes(StandardCharsets.UTF_8));
	}

}
