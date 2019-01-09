package com.wondertek.self.spring.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 分布式任务简单实现
 * Created by wd on 2019/1/9.
 */
public class MyJob implements SimpleJob{

    @Override
    public void execute(ShardingContext shardingContext) {
        //获取总的分片总数
        int totalCount = shardingContext.getShardingTotalCount();

        //获取分片项
        int shardingItem = shardingContext.getShardingItem();

        //获取分片项参数
        String shardingParameter = shardingContext.getShardingParameter();

        System.out.println("分片总数：" + totalCount);
        System.out.println("分片项：" + shardingItem);
        System.out.println("分片项参数：" + shardingParameter);
        System.out.println("作业名称：" + shardingContext.getJobName());

        //不同的分片项进行不同的处理
        switch (shardingItem) {
            case 0:
                System.out.println("第1个分片项参数："+shardingParameter);
                break;
            case 1:
                System.out.println("第2个分片项参数："+shardingParameter);
                break;
            case 2:
                System.out.println("第3个分片项参数："+shardingParameter);
                break;
            case 3:
                System.out.println("第4个分片项参数："+shardingParameter);
                break;
            default:
                System.out.println("===========default========");
                break;
        }
    }
}
