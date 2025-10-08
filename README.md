Name(s) & Surname(s): Ntokozo Molefe (ST10376406) & Dinilethu Malatse (ST10264831) 
Module Code: OPSC6312  
Assignment: Part 2 (App Prototype Development & Documentation) 
 
Vaulted App – Prototype Documentation 
1.	Project Overview 
Vaulted is a mobile fashion and sneaker discovery platform designed for enthusiasts to stay updated on cultural releases, sneaker drops, and brand news. The app focuses on timely discovery rather than direct commerce, providing users with curated product information and links to verified retailers. 
Key Features in Prototype: 
•	User registration and login with encrypted password handling (via SessionManager, later extensible to Firebase Auth) 
•	User settings management (preferences, theme, notifications) 
•	Product browsing through a RESTful API 
•	Smooth navigation between multiple fragments 
•	Demonstration of asynchronous API calls, image loading, and local session management 
Target Audience: Fashion and sneaker enthusiasts who want a lightweight discovery app without in-app purchases. 
 
2.	Architecture & Design 
2.1	App Structure 
The app uses a fragment-based architecture under a single MainActivity: 
Fragment 	Purpose 
LoginFragment 	User authentication 
RegisterFragment 	User registration 
SettingsFragment 	Change preferences 
ProductFragment 	Display sneaker/fashion products
ProductDetailFragment 	Detailed view of selected product 
Navigation: Handled with the Android Navigation Component. 
2.2	Session Management 
A SessionManager handles: 
•	Login state 
•	Temporary user data 
•	User preferences 
It allows the app to function without a full backend during the prototype phase. 
 
3.	API Integration 
3.1	Sneaker Database API 
•	Type: Public RESTful API 
•	Purpose: Fetch sneaker data (brand, name, release date, image) 
•	Integration: Using Retrofit and Kotlin Coroutines 
•	Image Loading: Glide library Data Flow: 
1.	ProductFragment requests sneaker list via Retrofit 
2.	JSON response mapped to Kotlin data classes 
3.	RecyclerView displays product info 
4.	Clicking a product opens ProductDetailFragment 
3.2	External Libraries 
Library 	Purpose 
Retrofit 	API requests & JSON mapping 
Glide 	Image loading and caching 
Kotlin Coroutines 	Asynchronous calls 
Material Components 	UI design consistency 
 
4.	User Authentication & Encryption 
•	Current: SessionManager stores hashed password locally 
•	Future: Firebase Auth for secure authentication 
•	Flow: 
1.	User logs in/registers 
2.	SessionManager verifies credentials 
3.	Login state updated and user navigated to ProductFragment 
5.	Unit Testing 
•	Focus: API response parsing, session management, fragment navigation 
•	Tools: JUnit, AndroidX Test 
•	Goal: Validate individual components and data flow 
 
6.	Data Flow Diagram 
<img width="566" height="745" alt="image" src="https://github.com/user-attachments/assets/b4ee49ed-07ab-486a-a36c-6baf67f59a55" />
 
 
7.	Demo Video Checklist 
1.	User registration & login 
2.	Password encryption demonstration 
3.	Changing settings/preferences 
4.	Browsing products from Sneaker Database API 
5.	Viewing product details 
6.	Smooth navigation between fragments 
8.	Screenshots / Placeholder Diagram

Figure 1: Login Page 

<img width="558" height="578" alt="image" src="https://github.com/user-attachments/assets/23f55bb0-6261-41d8-881c-f6d88bc7f512" /> 

Figure 2: Registration Page 

<img width="783" height="723" alt="image" src="https://github.com/user-attachments/assets/7afcefc8-6737-4856-ae7f-c653ce18d1d6" />

Figure 3: Bottom Navbar Fragment 

<img width="633" height="553" alt="image" src="https://github.com/user-attachments/assets/76d27001-f4ad-407d-aec7-d53d904bb1d3" />

Figure 4: Main Homepage 
<img width="941" height="737" alt="image" src="https://github.com/user-attachments/assets/bd4693b0-1f46-4527-9a3b-9abb6f80c1d4" />

Figure 5: ProductView 
When the user open an image from the homepage, they will be navigated to this page for more details about the product and where to find to it.They can also add that item to their Wishlist. 

<img width="941" height="720" alt="image" src="https://github.com/user-attachments/assets/c6abc293-5a45-45a5-873a-414a6331dc48" />

Figure 6: Wishlist 
Items added to the Wishlist are saved and can be browsed using the navbar. Users can add as many items as they want in the Wishlist, and they will all show on this page. 

<img width="684" height="528" alt="image" src="https://github.com/user-attachments/assets/310d2f22-4e3e-48a6-bf1d-995d8a7377e1" />

Figure 7: Settings Page 

<img width="941" height="690" alt="image" src="https://github.com/user-attachments/assets/18a2ed5f-a78a-4eff-9e45-1e02a347cd1b" />


 YouTube: https://youtu.be/cRvOUmuQtv4?si=Ikfo19emenO_jxlR 


Conclusion 
The Vaulted prototype demonstrates a functional, user-friendly app with RESTful API integration, session management, and modular architecture.
It lays the foundation for full POE submission with future backend and feature enhancements. 
 
