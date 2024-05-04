package br.com.acmepay;

import br.com.acmepay.application.domain.exception.CheckEmailDocumentException;
import br.com.acmepay.application.domain.models.CustomerDomain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class AcmePayApplication {

	public static void main(String[] args) throws CheckEmailDocumentException {
		//SpringApplication.run(AcmePayApplication.class, args);

		CustomerDomain cliente1 = new CustomerDomain();
		cliente1.setId(1L);
		cliente1.setName("Bruno");
		cliente1.setEmail("clinte1@gmail");
		cliente1.setPhone("1111111");
		cliente1.setDocument("123456789");

		CustomerDomain cliente2 = new CustomerDomain(2L, "Leticia", "let@gmail.com", "1111110", "555555", LocalDateTime.now(), null);

		//System.out.println(cliente1);
		//System.out.println(cliente2);

		CustomerDomain cliente3 = new CustomerDomain();
		cliente3.createCustomer(cliente1);
		cliente3.createCustomer(cliente2);

		cliente3.listUser();
		cliente3.delete(1L);
		System.out.println("Deu certo");
		cliente3.listUser();
		cliente3.getByCustumerCPF("555555");

		System.out.println("Deu certo --------------------");

		CustomerDomain testesUp = new CustomerDomain();
		testesUp.setId(3L);
		testesUp.setName("jose");
		testesUp.setEmail("joseph@gmail");
		testesUp.setPhone("151515");
		testesUp.setDocument("23456789");

		cliente3.createCustomer(testesUp);
		cliente3.listUser();

		System.out.println("--------------------");
		cliente3.update(2L, testesUp);
		cliente3.listUser();

		System.out.println("TESTE VALIDAÇÃO DE ERRO");
		CustomerDomain testesDown = new CustomerDomain();
		testesDown.setId(4L);
		testesDown.setName("Enzo");
		testesDown.setEmail("joseph@gmail");
		testesDown.setPhone("999999999999");
		testesDown.setDocument("23456789");

		cliente3.createCustomer(testesDown);
		cliente3.listUser();
	}

}
