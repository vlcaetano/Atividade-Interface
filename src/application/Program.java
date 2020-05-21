package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date: ");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		System.out.print("Enter number of installments: ");
		int months = sc.nextInt();
		
		Contract contract = new Contract(number, date, totalValue);
		
		ContractService cs = new ContractService(new PaypalService());
		
		cs.processContract(contract, months);
		
		
		for (Installment ins : contract.getInstallments()) {
			System.out.println(ins.toString());
		}
		
		
		sc.close();
	}
}
