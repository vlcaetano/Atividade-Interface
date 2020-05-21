package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService ops;

	public ContractService(OnlinePaymentService ops) {
		this.ops = ops;
	}

	public void processContract(Contract contract, Integer months) {	
		double amountPerMonth = (double)contract.getTotalValue() / months;
		double amount;
		Date date;

		for (int i = 1; i <= months; i++) {
			amount = amountPerMonth;
			
			amount += ops.interest(amount, i);
			amount += ops.paymentFee(amount);
			
			date = addMonths(contract.getDate(), i);
			
			contract.addInstallment(new Installment(date, amount));
		}		
		
		
	}
	
	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}	
}
