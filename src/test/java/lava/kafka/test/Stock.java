package lava.kafka.test;



public class Stock {

	String stockCode, stockName;
	long radeTime;
	
	
	float preClosePrice;


	public String getStockCode() {
		return stockCode;
	}


	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}


	public String getStockName() {
		return stockName;
	}


	public void setStockName(String stockName) {
		this.stockName = stockName;
	}


	public long getRadeTime() {
		return radeTime;
	}


	public void setRadeTime(long radeTime) {
		this.radeTime = radeTime;
	}


	public float getPreClosePrice() {
		return preClosePrice;
	}


	public void setPreClosePrice(float preClosePrice) {
		this.preClosePrice = preClosePrice;
	}


	
	
	

}
