package com.sistema.contas.model;



import com.sistema.contas.repository.*;
import com.sistema.contas.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class ContasController {


    @Autowired
    ContaCopelRepository contaCopelRepository;

    @Autowired
    ContaSaneparRepository contaSaneparRepository;

    @Autowired
    ContaSercomtelRepository contaSercomtelRepository;

    @Autowired
    LocalCopelRepository localCopelRepository;

    @Autowired
    LocalSaneparRepository localSaneparRepository;

    @Autowired
    LocalSercomtelRepository localSercomtelRepository;


    @GetMapping(value={"/index", "/"})
    public String mostrarListaContas(Model model) {

        List<Map<String, Object>> contas = new ArrayList<>();

        // COPEL
        for (ContaCopel conta : contaCopelRepository.findAll()) {
            Map<String, Object> item = new HashMap<>();
            item.put("grupo", "COPEL");
            item.put("id", conta.getId());
            item.put("valor", conta.getValor());
            item.put("orgao", conta.getLocalCopel().getOrgao());
            item.put("endereco", conta.getLocalCopel().getEndereco());
            item.put("vencimento", conta.getVencimento());
            contas.add(item);
        }

        // SANEPAR
        for (ContaSanepar conta : contaSaneparRepository.findAll()) {
            Map<String, Object> item = new HashMap<>();
            item.put("grupo", "SANEPAR");
            item.put("id", conta.getId());
            item.put("valor", conta.getValor());
            item.put("orgao", conta.getLocalSanepar().getOrgao());
            item.put("endereco", conta.getLocalSanepar().getEndereco());
            item.put("vencimento", conta.getVencimento());
            contas.add(item);
        }

        // SERCOMTEL
        for (ContaSercomtel conta : contaSercomtelRepository.findAll()) {
            Map<String, Object> item = new HashMap<>();
            item.put("grupo", "SERCOMTEL");
            item.put("id", conta.getId());
            item.put("valor", conta.getValor());
            item.put("orgao", conta.getLocalSercomtel().getOrgao());
            item.put("endereco", conta.getLocalSercomtel().getEndereco());
            item.put("vencimento", conta.getVencimento());
            contas.add(item);
        }

        model.addAttribute("contas", contas);

        return "index";
    }

    @GetMapping("/conta/{grupo}/{id}")
    public String mostrarConta(@PathVariable("grupo") String grupo, @PathVariable("id") int id, Model model){

        Map<String, Object> item = new HashMap<>();

        switch (grupo) {
            case "COPEL" -> {
                ContaCopel contaCopel = contaCopelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválida" + id));
                item.put("id", contaCopel.getId());
                item.put("mes", contaCopel.getMes());
                item.put("ano", contaCopel.getAno());
                item.put("vencimento", contaCopel.getVencimento());
                item.put("valor", contaCopel.getValor());
                item.put("kwhPonta", contaCopel.getKwhPonta());
                item.put("kwhFora", contaCopel.getKwhFora());
                item.put("kwhTotal", contaCopel.getKwhTotal());
                item.put("numeroProtocolo", contaCopel.getNumeroProtocolo());
                item.put("dataEnvioPagamento", contaCopel.getDataEnvioPagamento());
                item.put("contaInativa", contaCopel.getContaInativa());
                item.put("endereco", contaCopel.getLocalCopel().getEndereco());
                item.put("idCopel", contaCopel.getLocalCopel().getIdCopel());
                item.put("orgao", contaCopel.getLocalCopel().getOrgao());
                item.put("idLocalCopel",contaCopel.getLocalCopel().getId());
                model.addAttribute("conta", item);
                return "conta-copel";
            }
            case "SANEPAR" -> {
                ContaSanepar contaSanepar = contaSaneparRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválida" + id));
                item.put("id", contaSanepar.getId());
                item.put("mes", contaSanepar.getMes());
                item.put("ano", contaSanepar.getAno());
                item.put("vencimento", contaSanepar.getVencimento());
                item.put("valor", contaSanepar.getValor());
                item.put("m3", contaSanepar.getM3());
                item.put("numeroProtocolo", contaSanepar.getNumeroProtocolo());
                item.put("dataEnvioPagamento", contaSanepar.getDataEnvioPagamento());
                item.put("contaInativa", contaSanepar.getContaInativa());
                item.put("endereco", contaSanepar.getLocalSanepar().getEndereco());
                item.put("matriculaSanepar", contaSanepar.getLocalSanepar().getMatriculaSanepar());
                item.put("orgao", contaSanepar.getLocalSanepar().getOrgao());
                item.put("idLocalSanepar",contaSanepar.getLocalSanepar().getId());
                model.addAttribute("conta", item);
                return "conta-sanepar";
            }
            case "SERCOMTEL" -> {
                ContaSercomtel contaSercomtel = contaSercomtelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválida" + id));
                item.put("id", contaSercomtel.getId());
                item.put("mes", contaSercomtel.getMes());
                item.put("ano", contaSercomtel.getAno());
                item.put("vencimento", contaSercomtel.getVencimento());
                item.put("valor", contaSercomtel.getValor());
                item.put("kwhPonta", contaSercomtel.getNotaFiscal());
                item.put("numeroProtocolo", contaSercomtel.getNumeroProtocolo());
                item.put("dataEnvioPagamento", contaSercomtel.getDataEnvioPagamento());
                item.put("contaInativa", contaSercomtel.getContaInativa());
                item.put("tipoSercomtel", contaSercomtel.getLocalSercomtel().getTipo());
                item.put("endereco", contaSercomtel.getLocalSercomtel().getEndereco());
                item.put("inscricaoSercomtel", contaSercomtel.getLocalSercomtel().getInscricaoSercomtel());
                item.put("telefone", contaSercomtel.getLocalSercomtel().getTelefone());
                item.put("orgao", contaSercomtel.getLocalSercomtel().getOrgao());
                item.put("idLocalSercomtel",contaSercomtel.getLocalSercomtel().getId());
                model.addAttribute("conta", item);
                return "conta-sercomtel";
            }
            default ->
                throw new IllegalArgumentException("Grupo inválido: " + grupo);
        }
    }

    @GetMapping("/novo-local-copel")
    public String mostrarFormNovoLocalCopel(Model model) {
        LocalCopel local = new LocalCopel();
        model.addAttribute("local", local);
        return "novo-local-copel";
    }

    @PostMapping("/adicionar-local-copel")
    public String adicionarLocalCopel(@Valid @ModelAttribute("local") LocalCopel local, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "novo-local-copel";
        }
        LocalCopel localExistente = localCopelRepository.findByIdCopel(local.getIdCopel());

        if (localExistente != null) {
            result.rejectValue("idCopel", null, "Já existe um local com este ID Copel");
            return "novo-local-copel";
        }

        localCopelRepository.save(local);

        return "redirect:/index";
    }

    @GetMapping("/novo-local-sanepar")
    public String mostrarFormNovoLocalSanepar(Model model) {
        LocalSanepar local = new LocalSanepar();
        model.addAttribute("local", local);
        return "novo-local-sanepar";
    }

    @PostMapping("/adicionar-local-sanepar")
    public String adicionarLocalSanepar(@Valid @ModelAttribute("local") LocalSanepar local, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "novo-local-sanepar";
        }
        LocalSanepar localExistente = localSaneparRepository.findByMatriculaSanepar(local.getMatriculaSanepar());

        if (localExistente != null) {
            result.rejectValue("idSanepar", null, "Já existe um local com este ID Sanepar");
            return "novo-local-sanepar";
        }

        localSaneparRepository.save(local);

        return "redirect:/index";
    }

    @GetMapping("/novo-local-sercomtel")
    public String mostrarFormNovoLocalSercomtel(Model model) {
        LocalSercomtel local = new LocalSercomtel();
        model.addAttribute("local", local);
        return "novo-local-sercomtel";
    }

    @PostMapping("/adicionar-local-sercomtel")
    public String adicionarLocalSercomtel(@Valid @ModelAttribute("local") LocalSercomtel local, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "novo-local-sercomtel";
        }
        LocalSercomtel localExistente = localSercomtelRepository.findByInscricaoSercomtel(local.getInscricaoSercomtel());

        if (localExistente != null) {
            result.rejectValue("idSercomtel", null, "Já existe um local com este ID Sercomtel");
            return "novo-local-sercomtel";
        }

        localSercomtelRepository.save(local);

        return "redirect:/index";
    }

    @GetMapping("/nova-conta-copel")
    public String mostrarFormNovaContaCopel(Model model) {
        ContaCopel conta = new ContaCopel();
        conta.setLocalCopel(new LocalCopel());
        model.addAttribute("conta", conta);
        return "nova-conta-copel";
    }

    @PostMapping("/adicionar-conta-copel")
    public String adicionarContaCopel(@Valid @ModelAttribute("conta") ContaCopel conta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "nova-conta-copel";
        }

        String idCopel = conta.getLocalCopel().getIdCopel();
        LocalCopel local = localCopelRepository.findByIdCopel(idCopel);

        if (local == null) {
            result.rejectValue("localCopel.idCopel", null, "ID Copel não encontrado");
            return "nova-conta-copel";
        }

        conta.setLocalCopel(local);
        contaCopelRepository.save(conta);

        return "redirect:/index";
    }


    @GetMapping("/nova-conta-sanepar")
    public String mostrarFormNovaContaSanepar(Model model) {
        ContaSanepar conta = new ContaSanepar();
        conta.setLocalSanepar(new LocalSanepar());
        model.addAttribute("conta", conta);
        return "nova-conta-sanepar";
    }

    @PostMapping("/adicionar-conta-sanepar")
    public String adicionarContaSanepar(@Valid @ModelAttribute("conta") ContaSanepar conta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "nova-conta-sanepar";
        }

        String idSanepar = conta.getLocalSanepar().getMatriculaSanepar();
        LocalSanepar local = localSaneparRepository.findByMatriculaSanepar(idSanepar);

        if (local == null) {
            result.rejectValue("localSanepar.idSanepar", null, "ID Sanepar não encontrado");
            return "nova-conta-sanepar";
        }

        conta.setLocalSanepar(local);
        contaSaneparRepository.save(conta);

        return "redirect:/index";
    }

    @GetMapping("/nova-conta-sercomtel")
    public String mostrarFormNovaContaSercomtel(Model model) {
        ContaSercomtel conta = new ContaSercomtel();
        conta.setLocalSercomtel(new LocalSercomtel());
        model.addAttribute("conta", conta);
        return "nova-conta-sercomtel";
    }

    @PostMapping("/adicionar-conta-sercomtel")
    public String adicionarContaSercomtel(@Valid @ModelAttribute("conta") ContaSercomtel conta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "nova-conta-sercomtel";
        }

        String idSercomtel = conta.getLocalSercomtel().getInscricaoSercomtel();
        LocalSercomtel local = localSercomtelRepository.findByInscricaoSercomtel(idSercomtel);

        if (local == null) {
            result.rejectValue("localSercomtel.idSercomtel", null, "ID Sercomtel não encontrado");
            return "nova-conta-sercomtel";
        }

        conta.setLocalSercomtel(local);
        contaSercomtelRepository.save(conta);

        return "redirect:/index";
    }

    @GetMapping("/editar-conta-copel/{id}/{idLocalCopel}")
    public String mostrarFormAtualizarContaCopel(@PathVariable("idLocalCopel") int idLocalCopel,@PathVariable("id") int id, Model model) {
        ContaCopel contaCopel = contaCopelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválido:" + id));

        model.addAttribute("contaCopel", contaCopel);
        model.addAttribute("idLocalCopel", idLocalCopel);
        return "atualizar-conta-copel";
    }

    @PostMapping("/atualizar-conta-copel/{id}/{idLocalCopel}")
    public String atualizarContaCopel(@PathVariable("idLocalCopel") int idLocalCopel,@PathVariable("id") int id, @Valid ContaCopel contaCopel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            contaCopel.setId(id);
            return "atualizar-conta-copel";
        }

        LocalCopel localCopel = localCopelRepository.findById(idLocalCopel).orElseThrow(() -> new IllegalArgumentException("O id do Item é inválido:" + idLocalCopel));
        contaCopel.setLocalCopel(localCopel);
        contaCopelRepository.save(contaCopel);
        return "redirect:/index";
    }

    @GetMapping("/editar-conta-sanepar/{id}/{idLocalSanepar}")
    public String mostrarFormAtualizarContaSanepar(@PathVariable("idLocalSanepar") int idLocalSanepar,@PathVariable("id") int id, Model model) {
        ContaSanepar contaSanepar = contaSaneparRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválido:" + id));

        model.addAttribute("contaSanepar", contaSanepar);
        model.addAttribute("idLocalSanepar", idLocalSanepar);
        return "atualizar-conta-sanepar";
    }

    @PostMapping("/atualizar-conta-sanepar/{id}/{idLocalSanepar}")
    public String atualizarContaSanepar(@PathVariable("idLocalSanepar") int idLocalSanepar,@PathVariable("id") int id, @Valid ContaSanepar contaSanepar, BindingResult result, Model model) {
        if (result.hasErrors()) {
            contaSanepar.setId(id);
            return "atualizar-conta-sanepar";
        }

        LocalSanepar localSanepar = localSaneparRepository.findById(idLocalSanepar).orElseThrow(() -> new IllegalArgumentException("O id do Item é inválido:" + idLocalSanepar));
        contaSanepar.setLocalSanepar(localSanepar);
        contaSaneparRepository.save(contaSanepar);
        return "redirect:/index";
    }

    @GetMapping("/editar-conta-sercomtel/{id}/{idLocalSercomtel}")
    public String mostrarFormAtualizarContaSercomtel(@PathVariable("idLocalSercomtel") int idLocalSercomtel,@PathVariable("id") int id, Model model) {
        ContaSercomtel contaSercomtel = contaSercomtelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválido:" + id));

        model.addAttribute("contaSercomtel", contaSercomtel);
        model.addAttribute("idLocalSercomtel", idLocalSercomtel);
        return "atualizar-conta-sercomtel";
    }

    @PostMapping("/atualizar-conta-sercomtel/{id}/{idLocalSercomtel}")
    public String atualizarContaSercomtel(@PathVariable("idLocalSercomtel") int idLocalSercomtel,@PathVariable("id") int id, @Valid ContaSercomtel contaSercomtel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            contaSercomtel.setId(id);
            return "atualizar-conta-sercomtel";
        }

        LocalSercomtel localSercomtel = localSercomtelRepository.findById(idLocalSercomtel).orElseThrow(() -> new IllegalArgumentException("O id do Item é inválido:" + idLocalSercomtel));
        contaSercomtel.setLocalSercomtel(localSercomtel);
        contaSercomtelRepository.save(contaSercomtel);
        return "redirect:/index";
    }

    @GetMapping("/remover-conta-copel/{id}")
    public String removerContaCopel(@PathVariable("id") int id) {

        ContaCopel contaCopel = contaCopelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválido:" + id));
        contaCopelRepository.delete(contaCopel);

        return "redirect:/index";
    }

    @GetMapping("/remover-conta-sanepar/{id}")
    public String removerContaSanepar(@PathVariable("id") int id) {

        ContaSanepar contaSanepar = contaSaneparRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválido:" + id));
        contaSaneparRepository.delete(contaSanepar);

        return "redirect:/index";
    }

    @GetMapping("/remover-conta-sercomtel/{id}")
    public String removerContaSercomtel(@PathVariable("id") int id) {

        ContaSercomtel contaSercomtel = contaSercomtelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("O id da conta é inválido:" + id));
        contaSercomtelRepository.delete(contaSercomtel);

        return "redirect:/index";
    }
}