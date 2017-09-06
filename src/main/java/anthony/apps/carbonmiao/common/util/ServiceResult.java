package anthony.apps.carbonmiao.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Service的通用返回结果
 *
 * @param <T> 返回结果数据类型
 */
public class ServiceResult<T> {
    /**
     * 结果数据
     */
    private T resultData;
    /**
     * 结果代码
     */
    private ServiceResultCode resultCode;
    /**
     * 消息
     */
    private String message;

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public ServiceResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ServiceResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess() {
        setResultCode(ServiceResultCode.SUCCESS);
    }

    public void setFailed() {
        setResultCode(ServiceResultCode.FAILED);
    }

    public void setFailed(String message) {
        setFailed();
        setMessage(message);
    }

    public boolean isSuccess() {
        return ServiceResultCode.SUCCESS.equals(resultCode);
    }

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }
}
