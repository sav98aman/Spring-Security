
Spring Security Notes::-- by Aman Kumar verma

introduction---Security is the process of protecting resources from unauthenticated and unauthorized users and allowing
		specific (authenticated and authorized) users to access these protected resources.
		
		Spring Security supports authentication and authorization at the HTTP request method’s invocation level.

*******************************************************
2. Authentication and Authorization
		-Authentication and authorization are the two major operations provided by Spring Security.

	Authentication---Authentication is the process of verifying the identity of a user or 
			entity that is trying to access a system or resource.
			
			It is an essential aspect of security that ensures that only authorized users can 
			access sensitive data or perform specific actions.	

			The goal of authentication is to establish the identity of a user or entity, 
			typically by requiring the user to provide credentials, 
			such as a username and password or other forms of identification, 
			like a biometric factor or a smart card


***********************************************************************************************************

	Authorization---Authorization is the process of determining what actions a user or 
			entity is allowed to perform after they have been authenticated.
			
			It involves checking whether a user has the necessary permissions or 
			privileges to access a resource or perform a specific action.
			
			Authorization is an essential aspect of security, as it ensures that 
			users can only access the data or perform actions that they are authorized to do.
			
			Authorization is typically implemented by defining a set of access rules or policies 
			that determine what resources a user can access and what actions they can perform. 
	
			These rules may be based on various criteria, such as the user's role, group membership, 
			or other attributes. For example, a user with an administrator role may have permission 
			to perform administrative tasks such as adding or deleting users, while a regular user 
			may only have permission to view or modify their own data.

*************************************************************************************************************

Introducing Basic Authentication---
			The traditional approaches for authentication, such as using the login page 
			and session identification, are bound to a web-based client requiring human 
			interaction. 
			When it comes to communicating with the REST client, which may not even be 
			a web-based application, you need to think about the solution provided by 
			Basic Authentication.
			
  modern way-----------Basic Authentication is a standard HTTP header (Authorization) that sends 
			Base64-encoded credentials with each request.

			Base64 is not encrypted or hashed in any way; in other words, the username and
			password are encoded in clear-text format.

			The credential string contains the username and password in the 
			format username:password.


************************************************************************************************************
BasicAuthenticationFilter------------
			The BasicAuthenticationFilter object is responsible for processing any HTTP request
			that contains an HTTP request header of Authorization with an authentication schema
			of Basic Authentication and a Base64-encoded username:password token.
	
			If the authentication is successful, BasicAuthenticationFilter in Spring is responsible
			for putting the result (the Authentication object) into the SecurityContextHolder.

*************************************************************************************************************

enabling Spring Security ---- you just add one dependency in pom.xml file Spring Securtiy dependency

			<dependency>
 				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-security</artifactId>
			</dependency>

		Note--Once you add the Spring Security dependency to the Maven pom.xml file, 
			your entire application is protected via HTTP Basic Authentication 
			for all resources except common static resources (CSS files, JavaScript
			files, and so on), and an AuthenticationManager bean with an in-memory 
			default user is created for your application.

			this Dependecncy automatic provied one Authorization or provied from  bassed authorization
				by default username is --- user 
				and password -----------Spring and automatic by password showing in console


			you can also modifed by deafult UserName and Password 
				you just add two properties in aapplication.propertie
					
					spring.security.user.username=user
					spring.security.user.password=1234;

				or
					security.user.name = user
					security.user.password = password
					security.user.role = ADMIN

	you can also customise the Spring security defaults through ovverride method 

***************************************************************************************************************
	
Overriding the Spring Security Defaults--
		you can customise the Authentication ,Authentication manager and Authentication provider

	befor Customise these things 
		you can just understand the Authentication , Authentication Manager ,Authentication manager.
		
		or Filter--



without spring secrity
	clinet send http request-----dirctly goes to the servelt

with Spring security

						
							
					(defaults)Dao-AuthenticationProvider	LDAP-AuthenticationProvider	Custom-AuthenticationProvider
							|					|			|
							-----------------------------------------------------------------
											|
			Authentication							|	
				|							|	
clinet-->--http---->---------FilterChain---------Authenticaion Manager------Authentication Provider--------UserDetailsService
				|										|
			Spring Context Security									|
														|
								---------------------------------------------------------
								|				|			|	
						InMemoryUserDeatilsService	customUserDetailsService	JdbcUserDetailsService			
								|				|			|
								|				|			|
								|				|			|
							    UserDetails			  UserDetails		   Userdetails



clinet sent the http request that time check user is Authneticated or not-if user is not Authneticated
	then user need to login first then acces the httpservice--
		user login with Spring security --
					first Sent the data like Username or password ---Filter created a Authentication Object 
					second sent to next ad Authentication Manager (is Interface ) its extend Authentication Provider---call 
					The Dao-Authentication Provider

					3rd - Authentcation Provider implemnets the UserdetailsService
					4th userdetails Service provider Bydeafults InmemoryAuthentication--this is check user details vaild or not
					5th --- return Authentication Provider on object of Authentication this is Known as Principal of Authentication Object

					6th--- pricipal object -- go to the Secuirycontext-- and return Object of original from.

understanbale way--
	Other way--- chat GPT Answer
		It seems like you are describing the high-level process of authentication in a Spring Security application. 
		Here's a more detailed explanation of how this process works:

			A user sends an HTTP request to a protected resource.

			The Spring Security filter intercepts the request and checks if the user is authenticated. 
			If the user is not authenticated, the filter redirects the user to a login page or returns an error response.

			If the user needs to log in, they enter their username and password in a login form, which is submitted to the server.

			The username and password are sent to the server in an HTTP request.

			The Spring Security filter intercepts the login request and creates an Authentication object that contains the user's credentials.

			The Authentication object is passed to the Authentication Manager, which is responsible for authenticating the user.

			The Authentication Manager delegates authentication to one or more Authentication Providers, 
			which are responsible for verifying the user's credentials.

			The most common implementation of an Authentication Provider is the DaoAuthenticationProvider, 
			which uses a UserDetailsService to load the user's details (including the hashed password) from a database.

			The UserDetailsService implementation typically checks if the username is valid and if the user's account is enabled and not expired.

			If the user's credentials are valid, the Authentication Provider returns an Authentication object 
			that contains the user's principal (i.e., username) and authorities (i.e., roles).

			The Authentication object is passed back to the Authentication Manager, which sets the Authentication object in the SecurityContext.

			The user is redirected back to the original protected resource, which can now be accessed because the user is authenticated.

			Overall, this process involves multiple components, including filters, the Authentication Manager, Authentication Providers, 
			and UserDetailsService implementations. 
			By understanding the interactions between these components, 
			you can configure and customize the authentication process in your Spring Security application.




	
	
						
		