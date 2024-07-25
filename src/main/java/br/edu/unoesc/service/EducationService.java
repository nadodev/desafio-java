package br.edu.unoesc.service;

import br.edu.unoesc.models.Education;
import br.edu.unoesc.repositorio.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    public Education findById(Long id) {
        return educationRepository.findById(id).orElse(null);
    }

    public Education save(Education education) {
        return educationRepository.save(education);
    }

    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }
}