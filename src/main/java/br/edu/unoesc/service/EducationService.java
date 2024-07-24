package br.edu.unoesc.service;

import br.edu.unoesc.education.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    // Methods for creating, updating, deleting, and finding Educations
}
