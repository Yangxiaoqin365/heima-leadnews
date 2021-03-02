package com.heima;

import com.sun.javafx.css.CalculatedValue;
import io.mycat.config.model.rule.RuleAlgorithm;
import io.mycat.route.function.AbstractPartitionAlgorithm;

public class HeiMaBurstRuleAlgorithm extends AbstractPartitionAlgorithm implements RuleAlgorithm {
    //单组数据容量
    private Long volume;

    //单组DN节点数量
    private Integer step;

    //分片模
    private Integer mod;

    /**
     *算法设计-精确查询
     * @param columnValue 2-1  3-2
     * @return
     */
    @Override
    public Integer calculate(String columnValue) {

        if (columnValue != null) {
            String[] temp = columnValue.split("-");
            if (temp.length == 2) {
                try {
                    Long dataId = Long.valueOf(temp[0]);
                    Long burstId = Long.valueOf(temp[1]);
                    int group = (int) (dataId / volume) * step;
                    int pos = group + (int) (burstId / mod);
                    System.out.println("HEIMA RULE INFO ["+columnValue+"]-[{"+pos+"}]");
                    return pos;

                } catch (NumberFormatException e) {
                    System.out.println("HEIMA RULE INFO ["+columnValue+"]-[{"+e.getMessage()+"}]");
                }
            }
        }
        return 0;
    }

    /**
     * 范围计算
     * @param beginValue
     * @param endValue
     * @return
     */
    @Override
    public Integer[] calculateRange(String beginValue, String endValue) {
        if (beginValue != null && endValue != null) {
            //调用精确查询方法获取分片id
            Integer begin = calculate(beginValue);
            Integer end = calculate(endValue);
            if (begin == null || end == null) {
                return new Integer[0];
            }

            if (end >= begin) {
                int len =end-begin+1;
                Integer[] re = new Integer[len];
                for (int i = 0; i < re.length; i++) {
                    re[i] =begin + i;
                }
                return re;
            }
        }
        return new Integer[0];
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }
}
