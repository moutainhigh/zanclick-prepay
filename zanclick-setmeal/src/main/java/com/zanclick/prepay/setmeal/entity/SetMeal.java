package com.zanclick.prepay.setmeal.entity;

import com.zanclick.prepay.common.entity.Identifiable;
import lombok.Data;

/**
 * 资金授权商户
 *
 * @author duchong
 * @date 2019-7-8 14:19:20
 */
@Data
public class SetMeal implements Identifiable<Long> {

    private Long id;

    /**
     * 套餐编号
     */
    private String packageNo;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 总冻结金额
     */
    private String totalAmount;

    /**
     * 分期期数
     **/
    private Integer num;

    /**
     * 每期金额
     **/
    private String amount;

    /**
     * 标题
     */
    private String title;

    private Integer state;

    public enum State {
        open(1, "开启"),
        closed(0, "关闭的");

        private Integer code;
        private String desc;

        State(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public Boolean isOpen() {
        return state.equals(State.open.getCode());
    }

    public Boolean isClosed() {
        return state.equals(State.closed.getCode());
    }
}