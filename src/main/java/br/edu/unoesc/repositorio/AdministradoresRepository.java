package br.edu.unoesc.repositorio;

import br.edu.unoesc.models.Administrator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdministradoresRepository extends CrudRepository<Administrator, Long> {

    @Query( value = "SELECT CASE  WHEN COUNT(1) > 0 THEN true ELSE false END FROM Administrators WHERE id = :id", nativeQuery = true)
    public boolean existsByIdQ(Long id);

    @Query(value = "select * from administrators where email = :email and password = :password", nativeQuery = true)
    public Administrator Login(String email, String password);

    Administrator findByEmail(String email);


}
