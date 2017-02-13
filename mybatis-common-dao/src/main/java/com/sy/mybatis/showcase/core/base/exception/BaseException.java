package com.sy.mybatis.showcase.core.base.exception;

/**
 * 异常基类，所有的异常都要继承此异常
 * @author shiyu
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public BaseException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		msg = String.format(msgFormat, args);
	}

	public BaseException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public BaseException newInstance(String msgFormat, Object... args) {
		return new BaseException(code, msgFormat, args);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message) {
		super(message);
	}
}
