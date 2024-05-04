package br.com.acmepay.application.domain.models;

import br.com.acmepay.application.domain.exception.CheckEmailDocumentException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDomain {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    //private List<Account> accounts;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    private List<CustomerDomain> listCustomerDomain = new ArrayList<>();

    public void createCustomer(CustomerDomain customerDomain) throws CheckEmailDocumentException {
        if(checkEmailDocument(customerDomain)){
            this.setId(customerDomain.id);
            this.setName(customerDomain.name);
            this.setEmail(customerDomain.email);
            this.setPhone(customerDomain.phone);
            this.setDocument(customerDomain.document);
            this.setCreated_at(LocalDateTime.now());
            this.setUpdate_at(null);
            this.listCustomerDomain.add(customerDomain);
        }else{
            throw new CheckEmailDocumentException("Error");
        }
    }

    public CustomerDomain(Long id, String name, String email, String phone, String document, LocalDateTime created_at, LocalDateTime update_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public boolean checkEmailDocument(CustomerDomain customerDomain) {
        return listCustomerDomain.stream().noneMatch(c -> c.getEmail().equals(customerDomain.getEmail()) ||
                c.getDocument().equals(customerDomain.getDocument()));
    }

    public void listUser(){
        for (CustomerDomain clientes:listCustomerDomain){
            System.out.println(clientes);
        }
    }

    public void delete(Long customerId){
        listCustomerDomain.removeIf(c -> c.getId().equals(customerId));
    }

    public void update(Long id, CustomerDomain customerDomain){
        for (CustomerDomain clientes : listCustomerDomain){
            if(clientes.getId().equals(id)){
                clientes.setName(customerDomain.getName());
                clientes.setEmail(customerDomain.getEmail());
                clientes.setPhone(customerDomain.getPhone());
                clientes.setDocument(customerDomain.getDocument());
                clientes.setCreated_at(null);
            }
        }
    }

    public void getByCustumerCPF(String document){
        for(CustomerDomain clientes : listCustomerDomain){
            if(clientes.getDocument().equals(document)){
                System.out.println(clientes);
            }
        }
    }
}
