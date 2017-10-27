package com.gerald.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * 这里测试了zookeeper的构造器watcher和命令(例如exist、delete等)watcher的作用。
 * 
 * <p>
 * zk的每个命令都有两种设置watcher的方法：{@link ZooKeeper#exists(String, Watcher)}和{@link ZooKeeper#exists(String, boolean)}
 * <ol>
 *   <li>使用第一种方法，则监听节点上发生了特定的事件，则使用命令指定的wacher(例如{@link ZooKeeper#exists(String, Watcher)})</li>
 *   <li>使用第二种方法，则监听节点上发生了特定的事件，则使用构造器上指定的watcher</li>
 * </ol>
 * </p>
 * 
 * <p>
 * zookeeper除了命令监控事件(节点创建、删除、更新、子节点创建)，还有连接事件(connected、disconnected、expired)。与命令监控事件不同，
 * 连接事件会同时出发构造器watcher和命令监控watcher
 * </p>
 */
public class ZookeeperTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 30000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("default watcher, type = " + event.getType() + ", state = " + event.getState());
            }
        }, false);
        
//        exist(zk);
        
        TimeUnit.SECONDS.sleep(3600);
    }
    
    private static void exist(ZooKeeper zk) throws KeeperException, InterruptedException {
        zk.exists("/ab", new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("exist watcher, type = " + event.getType() + ", state = " + event.getState());
                try {
                    exist(zk);
                } catch (KeeperException | InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        });
    }
}
