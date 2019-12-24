package java8.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Datas {

	public static void main(String[] args) {
		LocalDate data = LocalDate.now();
		System.out.println(data);
		
		LocalDate futuro = LocalDate.of(2020, Month.JUNE, 4);
		
		int anos = futuro.getYear() - data.getYear();
		
		System.out.println(anos);
		
		Period periodo = Period.between(data, futuro);
		System.out.println(periodo.getYears() + periodo.getDays() + periodo.getMonths());
		
		System.out.println(futuro.plusYears(5));
		
		System.out.println(futuro.minusDays(5));
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println(formatador.format(futuro));
		
		DateTimeFormatter formatadorComHoras = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
		
		LocalDateTime agora = LocalDateTime.now();
		
		LocalTime hora = LocalTime.of(15, 30);
		
		System.out.println(agora.format(formatadorComHoras));
	}

}
