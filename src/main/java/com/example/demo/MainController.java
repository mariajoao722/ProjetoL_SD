package com.example.demo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // This means that this class is a Controller

public class MainController {
	
  @Autowired 
  private UserRepository userRepository;
  
  @Autowired
  private PropriedadeRepository propriedadeRepository;
  
  @Autowired
  private FavoritosRepository favRepository;
  
  private User curUser;
  
  

  @GetMapping(path="/")
	public String getAllUsers (Model model) {
		model.addAttribute("ListUsers" , userRepository.findAll());
		model.addAttribute("ListPropriedade", propriedadeRepository.findAll());
		model.addAttribute("ListFav",favRepository.findAll());
		return "index";
	}
  
  //-------------------REGUISTO----------------------------------------------
	
  @GetMapping("/showNewUserForm")
  public String showNewUserForm(Model model) {
//create model attribute to bind form data
      User users = new User();
      model.addAttribute("newuser", users);
      return "new_user";
  }
 
  
  @PostMapping("/registerUser")
	public String registerUser(Model model, @ModelAttribute("registeruser") User user, @RequestParam("regnome") String regnome, @RequestParam("regpassword") String regpassword, @RequestParam("regconfpassword") String regconfpassword, @RequestParam("regrole") String regrole) {
		
		
		Iterable<User> users = userRepository.findAll();
		
		boolean canReg = true;
		for(User u : users) {
			if(u.getNome().equals(regnome)) {
				canReg = false;
			}
		}
		
		
		if(canReg && regpassword.equals(regconfpassword)) {
			
			System.out.println("sucesso!");
			
			User newUser = new User();
			newUser.setId((int)userRepository.count() + 1);
			newUser.setNome(regnome);
			newUser.setPassword(regpassword);
			newUser.setRole(regrole);
			newUser.setVendas(0);
			
			System.out.println(newUser.toString());
			
			
			userRepository.save(newUser);
			
			return "redirect:/";

		} else {
			
			System.out.println("erro!");
			
			return "redirect:/showNewUserForm";
	
		}
	
	}
  
  @GetMapping("/deleteUser/{id}")
  public String deleteUser(@PathVariable(value = "id") Integer id) {
      userRepository.deleteById(id);
      return "redirect:/";
  }
  
  
  //----------------LOGIN--------------------------------------------
  
  @PostMapping("/loginUser")
	public String loginUser(Model model, @ModelAttribute("loginuser") User user, @RequestParam("lognome") String nome, @RequestParam("logpassword") String password) {
		
		
		Iterable<User> users = userRepository.findAll();
		if(!nome.equals("") && !password.equals("")) {
			for(User u : users) {
				if(u.getNome().equals(nome)) {
					if(u.getPassword().equals(password)) {
						//Login com sucesso
						System.out.println("Sucesso!");
						
						curUser = new User();
						curUser.setId(u.getId());
						curUser.setNome(u.getNome());
						curUser.setPassword(u.getPassword());
						curUser.setRole(u.getRole());
								
						return "redirect:loadUserRole";
					} 
				} 
			}
		}
		
		return "redirect:/";
	}
  
  @GetMapping("/loadUserRole")
	public String loadUserRole(Model model) {
		
		
	    System.out.println(curUser.toString());
		model.addAttribute("curUser", curUser);
		if(curUser.getRole().equals("cliente")) {
			return "main_page";
		} else {
			return "admin_page";
		}
		
	}
  
  
  //--------------------------PROPRIEDADE VENDEDOR---------------------
  
  @GetMapping("/showNewPropriedadeForm")
  public String showNewPropriedadeForm(Model model) {
//create model attribute to bind form data
      Propriedade prop = new Propriedade();
      model.addAttribute("newpropriedade", prop);
      return "new_prop";
  }
  
  
  @PostMapping("/savePropriedade")
	public String savePropriedade (@ModelAttribute("newprop") Propriedade propriedade, @RequestParam("propnome") String propnome, @RequestParam("proppreco") Double proppreco, @RequestParam("proplocal") String proplocal, @RequestParam("proparea") int proparea, @RequestParam("proptipo") String proptipo, @RequestParam("propcompra") String propcompra) {
	 // save prop to database
	  
	  Iterable<Propriedade> props = propriedadeRepository.findAll();
		
		boolean canAdc = true;
		for(Propriedade p : props) {
			if(p.getNome().equals(propnome)) {
				canAdc = false;
			}
		}
		
		
		if(canAdc) {
			
			System.out.println("sucesso!");

			Propriedade newprop = new Propriedade();

			newprop.setId((int)propriedadeRepository.count()+1);
			newprop.setNome(propnome);
			newprop.setPreco(proppreco);
			newprop.setUserid(curUser.getId()); //id do vendedor
			newprop.setLocal(proplocal);
			newprop.setArea(proparea);
			newprop.setTipodeimovel(proptipo);
			newprop.setCompra_aluga(propcompra);
			newprop.setDisponivel(0);

			System.out.println(newprop.toString());

			propriedadeRepository.save(newprop);
				
		} else {
			
			System.out.println("erro!");
			
		}

	  return "redirect:/loadUserRole";
	
	}
  
  @GetMapping("/listPropriedades")
	public String listPropriedades(Model model) {
		 Iterable<Propriedade> propriedade = propriedadeRepository.findAll();
		 Iterable<User> users = userRepository.findAll();
		 ArrayList<PropriedadesList> propList = new ArrayList<>();
		 
		 String curName = "";
		 for(Propriedade p: propriedade) {
			  
			 for(User u : users) {
				 if(u.getId() == p.getUserid()) {
					 curName = u.getNome();
					 if(curName.equals(curUser.getNome())) {
						 propList.add(new PropriedadesList(p.getId(), p.getNome(), p.getPreco(), curName, p.getLocal(),p.getArea(),p.getTipodeimovel(),p.getCompra_aluga(),p.getDisponivel()));
					 }
					 
				 }
			 }
			
		 }
		
		 model.addAttribute("ListPropriedade", propList);
		 
		 return "list_propriedades";
	}
  
  
  //----------------------------PROPRIEDADE CLIENTE---------------------------------
  
  @GetMapping("/listPropriedadesCliente")
 	public String listPropriedadesCliente(Model model) {
 		 Iterable<Propriedade> propriedade = propriedadeRepository.findAll();
 		 Iterable<User> users = userRepository.findAll();
 		 ArrayList<PropriedadesList> propList = new ArrayList<>();
 		 
 		 String curName = "";
 		 for(Propriedade p: propriedade) {
 			  
 			 for(User u : users) {
 				 if(u.getId() == p.getUserid() && p.getDisponivel() == 0) {
 					 curName = u.getNome();
 					propList.add(new PropriedadesList(p.getId(), p.getNome(), p.getPreco(), curName, p.getLocal(),p.getArea(),p.getTipodeimovel(),p.getCompra_aluga(),p.getDisponivel()));	 
 					 
 				 }
 			 }
 			
 		 }
 		
 		 model.addAttribute("ListPropriedade", propList);
 		 
 		 return "list_propriedades_cliente";
 	}
  
  
 /* @PostMapping("/compracasa")
  public String compracasa(@RequestParam("propriedadeId") int propriedadeId) {
	  Iterable<Propriedade> propriedades = propriedadeRepository.findAll();

      for (Propriedade propriedade : propriedades) {
          if (propriedade.getId() == propriedadeId) {
              propriedade.setDisponivel(1);
              propriedadeRepository.save(propriedade);
              break;
          }
      }

      // Redirect to the page where the table is displayed
      return "redirect:/listPropriedadesCliente";
  }*/
  

  @PostMapping("/compracasa")
  public String compracasa(@RequestParam("propriedadeId") int propriedadeId) {
	  Iterable<Propriedade> propriedades = propriedadeRepository.findAll();
	  Iterable<User> users = userRepository.findAll();
	  Iterable<Favoritos> favoritos = favRepository.findAll();


	  for (Propriedade propriedade : propriedades) {
		  if (propriedade.getId() == propriedadeId) {
			  propriedade.setDisponivel(1);
			  propriedadeRepository.save(propriedade);

			  for(Favoritos f : favoritos) {
				  if (f.getNome().equals(propriedade.getNome())) {
					  f.setDisponivel(1);
					  favRepository.save(f);
				  }
			  }

			  for(User u : users) {
				  if(u.getId() == propriedade.getUserid()) {
					  u.setVendas(u.getVendas() + 1);
					  userRepository.save(u);
				  }
			  }

			  if (propriedade.getTipodeimovel().equals("apartamento")) {
				  propriedade.setPropapartamento(propriedade.getPropapartamento() + 1);
			  }
			  if (propriedade.getTipodeimovel().equals("casa")) {
				  propriedade.setPropcasa(propriedade.getPropcasa() + 1);
			  }
			  if (propriedade.getTipodeimovel().equals("terreno")) {
				  propriedade.setPropterreno(propriedade.getPropterreno() + 1);
			  }

			  break;

		  }

	  }



	  // Redirect to the page where the table is displayed
	  return "redirect:/listPropriedadesCliente";
  }
  
  
  @GetMapping("/propriedadeDetails/{id}")
  public String showPropriedadeDetails(@PathVariable("id") int id, Model model) {
      Optional<Propriedade> propriedadeOptional = propriedadeRepository.findById(id);
      if (propriedadeOptional.isPresent()) {
          Propriedade propriedade = propriedadeOptional.get();
          model.addAttribute("propriedade", propriedade);
          return "propriedadeDetails";
      } else {
          // Handle the case when the property is not found
          return "error"; // Replace with your error page or appropriate response
      }
  }
  
  @GetMapping("/propriedadeDetailsfav/{id}")
  public String showPropriedadeDetailsfav(@PathVariable("id") int id, Model model) {
      Optional<Propriedade> propriedadeOptional = propriedadeRepository.findById(id);
      if (propriedadeOptional.isPresent()) {
          Propriedade propriedade = propriedadeOptional.get();
          model.addAttribute("propriedade", propriedade);
          return "propriedadeDetailsfav";
      } else {
          // Handle the case when the property is not found
          return "error"; // Replace with your error page or appropriate response
      }
  }
  
  
  
  //------------------------------FAVORITOS------------------------------------
  
  @GetMapping("/deleteProp/{id}")
  public String deleteProp(@PathVariable(value = "id") Integer id) {
      favRepository.deleteById(id);
      return "redirect:/listFavoritos"; // dar redirect para pagina de favoritos
  }
  
  
  @PostMapping("/addfavoritos")
  public String addfavoritos(@RequestParam("propriedadeId") int propriedadeId) {
	  Iterable<Propriedade> propriedades = propriedadeRepository.findAll();
	  //Iterable<Favoritos> favoritos = favRepository.findAll();

	  boolean canAdc = true;		

	  for (Propriedade propriedade : propriedades) {
		  /*for(Favoritos f : favoritos) {
			  if(f.getNome().equals(propriedade.getNome())) { // Rever condição
				  canAdc = false;
			  }

			  	//System.out.println(canAdc);
		  }*/
		  
		  if (canAdc) {
			

			  if (propriedade.getId() == propriedadeId) {	


				  System.out.println("sucesso!");

				  Favoritos favs = new Favoritos();

				  favs.setId((int)favRepository.count()+1);
				  favs.setNome(propriedade.getNome());
				  favs.setPreco(propriedade.getPreco());
				  favs.setLocal(propriedade.getLocal());
				  favs.setIdUser(curUser.getId());
				  favs.setArea(propriedade.getArea());
				  favs.setTipodeimovel(propriedade.getTipodeimovel());
				  favs.setCompra_aluga(propriedade.getCompra_aluga());
				  favs.setDisponivel(propriedade.getDisponivel());

				  System.out.println(favs.toString());

				  favRepository.save(favs);
				  break;
			  }

		  }

	  }


	  // Redirect to the page where the table is displayed
	  return "redirect:/listPropriedadesCliente";
  }
  
  @GetMapping("/listFavoritos")
	public String listFavoritos(Model model) {
		 Iterable<Favoritos> favorito = favRepository.findAll();
		 Iterable<User> users = userRepository.findAll();
		 ArrayList<FavoritosList> favList = new ArrayList<>();
		 
		 String curName = "";
		 for(Favoritos f: favorito) {			  
			 for(User u : users) {
				 if(u.getId() == f.getIdUser()) {
					 curName = u.getNome();
					 if(curName.equals(curUser.getNome()) && f.getDisponivel() == 0) {
						 favList.add(new FavoritosList(f.getId(), f.getNome(), f.getPreco(), curName, f.getLocal(),(int) f.getArea(),f.getTipodeimovel(),f.getCompra_aluga(),f.getDisponivel()));
					 }
					 
				 }
			 }
			
		 }
		
		 model.addAttribute("ListFav", favList);
		 
		 return "list_favorites";
	}
  
//------------------------------ESTATÍSTICAS------------------------------------
  @GetMapping("/getmaisvendas")
  public String getmaisvendas(Model model) {
      Iterable<User> users = userRepository.findAll();
      User maisvendas = null;
      int maxvendas = 0;

      for (User user : users) {
          if (user.getRole().equals("vendedor") && user.getVendas() > maxvendas) {
              maxvendas = user.getVendas();
              maisvendas = user;
          }
      }

      model.addAttribute("maisvendas", maisvendas);
      return "user_with_most_sales";
  }
  
  
  @GetMapping("/gettipomaisvendas")
  public String gettipomaisvendas(Model model) {
	  Iterable<Propriedade> propriedades = propriedadeRepository.findAll();
	  
	    String tipo = "";
  		int propcasa = 0;
        int propapartamento = 0;
        int propterreno = 0;
        
	    for(Propriedade p : propriedades) {
	    	propapartamento = p.getPropapartamento();
	    	propcasa = p.getPropcasa();
	    	propterreno = p.getPropterreno();
	    
	    if (propapartamento > propcasa && propapartamento > propterreno) {
	        tipo = "Apartamento";
	    } else if (propcasa > propapartamento && propcasa > propterreno) {
	        tipo = "Casa";
	    } else if (propterreno > propapartamento && propterreno > propcasa) {
	        tipo = "Terreno";
	    } else {
	        tipo = "No clear winner. Multiple property types have the same count.";
	    }
	    }
	    model.addAttribute("tipo", tipo);
	    return "tipo_mais_vendido";
	}

  
  @GetMapping("/propriedadesvendidas")
  public String getPropVendidasView(Model model) {
	  Iterable<User> users = userRepository.findAll();
	  int propvendidas = 0;
	  for(User u : users) {
		  propvendidas += u.getVendas();
	  }
	  
      model.addAttribute("propvendidas", propvendidas);
      return "propriedadesvendidas";
  }
  
  @GetMapping("/menosvendas")
  public String menosvendas(Model model) {
        Iterable<User> users = userRepository.findAll();
        User usermenos = null;
        int minvendas = Integer.MAX_VALUE;

        for (User user : users) {
            if (user.getRole().equals("vendedor")) {
                int vendas = user.getVendas();
                if (vendas < minvendas) {
                    minvendas = vendas;
                    usermenos = user;
                }
            }
        }

        model.addAttribute("usermenos", usermenos);
        return "user_with_less_sales";
    }
  
  
}