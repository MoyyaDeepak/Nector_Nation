package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.repo.UserRepository;
import com.app.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import jakarta.annotation.PostConstruct;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, Model model) {
		User existingUser=repo.findByEmail(user.getEmail());
		boolean phoneNumberExists=service.isPhoneNumberAlreadyExists(user.getPhoneNumber());
		if(existingUser!=null) {
			model.addAttribute("message", "User already exists. Please Signin");
			model.addAttribute("messageType", "danger");
			model.addAttribute("user", new User());
			return "register";
		}if(phoneNumberExists) {
			model.addAttribute("message", "This mobile number is already registered");
			model.addAttribute("messageType", "danger");
			model.addAttribute("user", new User());
			return "register";
		}
		
		service.saveUser(user);
		model.addAttribute("message", "Registration successful!");
		model.addAttribute("messageType", "success");
		return "redirect:/homePage";
		
//		else {
//			service.saveUser(user);
//			model.addAttribute("message", "Registration successful!");
//            model.addAttribute("messageType", "success");
//			return "redirect:/homePage";
//		}		
	}
	
	@GetMapping("/homePage")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/shop")
	public String shopPage() {
		return "shop";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
	@GetMapping("/wishlist")
	public String wishlistPage() {
		return "wishlist";
	}
	
	@GetMapping("/cart")
	public String cartPage() {
		return "cart";
	}
	
	@GetMapping("/checkout")
	public String checkoutPage() {
		return "checkout";
	}
	
	@GetMapping("/produts/checkout")
	public String PcheckoutPage() {
		return "checkout";
	}
	
	@GetMapping("/signin")
	public String signinView(Model model) {
		model.addAttribute("signInForm", new User());
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signin(@ModelAttribute User user, Model model) {
		String email=user.getEmail();
		String password=user.getPassword();
		
		if(service.validUser(email, password)) {
			model.addAttribute("message", "Login successful!");
            model.addAttribute("messageType", "success");
            return "redirect:/homePage";
        } else {
        	model.addAttribute("signInForm", new User());
        	model.addAttribute("message", "Invalid login credentials. (Or) Create a new account");
			model.addAttribute("messageType", "danger");
            return "signin";
		}
	}
	
	@GetMapping("/beepollencatalog")
	public String beepollen() {
		return "beepollencatalog";
	}
	@GetMapping("/beepropoliscatalog")
	public String beepropoliscatalog() {
		return "beepropoliscatalog";
	}
	@GetMapping("/honeycatalog")
	public String honeycatalog() {
		return "honeycatalog";
	}
	@GetMapping("/royalijellycatalog")
	public String royalijellycatalog() {
		return "royalijellycatalog";
	}
	@GetMapping("/snackbarcatalog")
	public String snackbarcatalog() {
		return "snackbarcatalog";
	}
	
	//royal jelly products
	@GetMapping("/produts/royaljellyhoney")
	public String royaljelly() {
		return "produts/royaljellyhoney";
	}
	@GetMapping("/produts/royaljelly")
	public String produts_royaljelly() {
		return "produts/royaljelly";
	}
	@GetMapping("/produts/royaljellycap")
	public String royaljellycap() {
		return "produts/royaljellycap";
	}
	@GetMapping("/produts/royaljellycream")
	public String royaljellycream() {
		return "produts/royaljellycream";
	}
	@GetMapping("/produts/royaljellyshots")
	public String royaljellyshots() {
		return "produts/royaljellyshots";
	}
	@GetMapping("/produts/royaljellysupplement")
	public String royaljellysupplement() {
		return "produts/royaljellysupplement";
	}
	
	//beepollen products
		@GetMapping("/produts/organicbee")
		public String organicbee() {
			return "produts/organicbee";
		}
		@GetMapping("/produts/beepollenblend")
		public String beepollenblend() {
			return "produts/beepollenblend";
		}
		@GetMapping("/produts/beepollencap")
		public String beepollencap() {
			return "produts/beepollencap";
		}
		@GetMapping("/produts/beepollengran")
		public String beepollengran() {
			return "produts/beepollengran";
		}
		@GetMapping("/produts/beepollenpowder")
		public String beepollenpowder() {
			return "produts/beepollenpowder";
		}
		@GetMapping("/produts/beepollentinc")
		public String beepollentinc() {
			return "produts/beepollentinc";
		}
		
	//honeycatalog products
	@GetMapping("/produts/cloverhoney")
	public String cloverhoney() {
		return "produts/cloverhoney";
	}
				@GetMapping("/produts/lavender")
				public String lavender() {
					return "produts/lavender";
				}
				@GetMapping("/produts/orangeblossom")
				public String orangeblossom() {
					return "produts/orangeblossom";
				}
				@GetMapping("/produts/wildflower")
				public String wildflower() {
					return "produts/wildflower";
				}
				@GetMapping("/produts/acacia")
				public String acacia() {
					return "produts/acacia";
				}
				@GetMapping("/produts/buckwheat")
				public String buckwheat() {
					return "produts/buckwheat";
				}
				
				//beepropolistinc products
				@GetMapping("/produts/beepropoliscap")
				public String beepropoliscap() {
					return "produts/beepropoliscap";
				}
				@GetMapping("/produts/beepropolishoney")
				public String beepropolishoney() {
					return "produts/beepropolishoney";
				}
				@GetMapping("/produts/beepropolisointment")
				public String beepropolisointment() {
					return "produts/beepropolisointment";
				}
				@GetMapping("/produts/beepropolisspray")
				public String beepropolisspray() {
					return "produts/beepropolisspray";
				}
				@GetMapping("/produts/beepropolistab")
				public String beepropolistab() {
					return "produts/beepropolistab";
				}
				@GetMapping("/produts/beepropolistinc")
				public String beepropolistinc() {
					return "produts/beepropolistinc";
				}
				
				//snackbar products
				@GetMapping("/produts/peanutbutterbar")
				public String peanutbutterbar() {
					return "produts/peanutbutterbar";
				}
				@GetMapping("/produts/questbar")
				public String questbar() {
					return "produts/questbar";
				}
				@GetMapping("/produts/oatsbar")
				public String oatsbar() {
					return "produts/oatsbar";
				}
				@GetMapping("/produts/honeyalmondbar")
				public String honeyalmondbar() {
					return "produts/honeyalmondbar";
				}
				@GetMapping("/produts/bumblebar")
				public String bumblebar() {
					return "produts/bumblebar";
				}
				@GetMapping("/produts/berryblissbar")
				public String berryblissbar() {
					return "produts/berryblissbar";
				}	
				
				//---- payment controller code
				@RestController
				@RequestMapping("/api/payment")
				public class PaymentController {

				    private RazorpayClient razorpayClient;

				    @Value("${razorpay.key}")
				    private String razorpayKey;

				    @Value("${razorpay.secret}")
				    private String razorpaySecret;

				    // This method initializes the RazorpayClient after the key and secret are injected
				    @PostConstruct
				    public void init() throws Exception {
				        this.razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);
				    }

				    @PostMapping("/createOrder")
				    public Map<String, Object> createOrder(@RequestBody Map<String, Object> orderRequest) {
				        Map<String, Object> response = new HashMap<>();
				        try {
				            // Convert the incoming map to JSONObject for Razorpay API compatibility
				            JSONObject orderData = new JSONObject(orderRequest);

				            // Create an order using Razorpay's API
				            Order order = razorpayClient.orders.create(orderData);

				            // Prepare the response with order details
				            response.put("id", order.get("id"));
				            response.put("amount", order.get("amount"));
				            response.put("currency", order.get("currency"));
				        } catch (Exception e) {
				            response.put("error", e.getMessage());
				        }
				        return response;
				    }

				    @PostMapping("/verifyPayment")
				    public Map<String, Object> verifyPayment(@RequestBody Map<String, String> paymentDetails) {
				        Map<String, Object> response = new HashMap<>();
				        String paymentId = paymentDetails.get("paymentId");
				        String orderId = paymentDetails.get("orderId");

				        // Here you can implement logic to verify the payment with Razorpay's API
				        // For now, we'll just simulate a success response.
				        response.put("success", true);  // Set this based on the actual verification result
				        response.put("paymentId", paymentId);
				        response.put("orderId", orderId);

				        // Add actual verification logic here using Razorpay API if needed.

				        return response;
				    }
				}		
}
