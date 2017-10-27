package com.gerald.test;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.ZooKeeper;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {
    	CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().canBeReadOnly(false)
                .connectionTimeoutMs(20000)
                .connectString("localhost:2181")
                .maxCloseWaitMs(60000)
                .retryPolicy(new BoundedExponentialBackoffRetry(100, 30000, Integer.MAX_VALUE))
                .sessionTimeoutMs(30000)
                .threadFactory((r)-> {
                    Thread thread = new Thread(r);
                    thread.setName("node");
                    return thread;
                })
                .build();
    	
    	curatorFramework.start();
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	curatorFramework.checkExists().forPath("/ab");
    }
}
