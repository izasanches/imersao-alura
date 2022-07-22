package br.com.alura.linguagens.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("linguagens")
    public List<Linguagem> obterLinguagens() {
        return repositorio.findAll();
    }

    @GetMapping("linguagens/{id}")
    public Linguagem obterLinguagem(@PathVariable String id) {
        return repositorio.findById(id).get();
    }

    @PostMapping("linguagens")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Linguagem cadastrarLinguagem(@RequestBody Linguagem linguagem){
        return repositorio.save(linguagem);
    }

    @PatchMapping("linguagens/{id}")
    public Linguagem alterarLinguagem(@RequestBody Linguagem linguagem, @PathVariable String id) {
        Linguagem linguagemModificada = repositorio.findById(id).orElseThrow(() -> new RuntimeException("Linguagem não encontrada!"));
        linguagemModificada.setTitle(linguagem.getTitle());
        linguagemModificada.setImage(linguagem.getImage());
        linguagemModificada.setRanking(linguagem.getRanking());

        return repositorio.save(linguagemModificada);

    }

    @DeleteMapping("linguagens/{id}")
    public void deletarLinguagem(@PathVariable String id){        
        repositorio.deleteById(id);
    }
    
}
