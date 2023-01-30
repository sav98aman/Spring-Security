# Spring-Security
Spring Security Basic


*By Aman Kumar*

*first understand Why Spring Security Is Requried*

    Spring Security is a framework that provides authentication, authorization, and protection against common attacks. With first class support for securing both imperative and reactive applications, it is the de-facto standard for securing Spring-based applications.
*******************************
*Prerequisites*
    
*   Spring Security requires a Java 8 or higher Runtime Environment.

*   As Spring Security aims to operate in a self-contained manner, you do not need to place any special configuration files in your Java Runtime Environment. In particular, you need not configure a special Java Authentication and Authorization Service (JAAS) policy file or place Spring Security into common classpath locations.

*    Similarly, if you use an EJB Container or Servlet Container, you need not put any special configuration files anywhere nor include Spring Security in a server classloader. All the required files are contained within your application.

*    This design offers maximum deployment time flexibility, as you can copy your target artifact (be it a JAR, WAR, or EAR) from one system to another and it immediately works.

******************************** 
*   *   Session Management 

        *   In Spring Security 5, the default behavior is for the SecurityContext to automatically be saved to the SecurityContextRepository using the SecurityContextPersistenceFilter.
        
        *   Saving must be done just prior to the HttpServletResponse being committed and just before SecurityContextPersistenceFilter.

        *   Unfortunately, automatic persistence of the SecurityContext can surprise users when it is done prior to the request completing (i.e. just prior to committing the HttpServletResponse).


        *   It also is complex to keep track of the state to determine if a save is necessary causing unnecessary writes to the SecurityContextRepository (i.e. HttpSession) at times.

        *   n Spring Security 6, the default behavior is that the SecurityContextHolderFilter will only read the SecurityContext from SecurityContextRepository and populate it in the SecurityContextHolder

        *   Users now must explicitly save the SecurityContext with the SecurityContextRepository if they want the SecurityContext to persist between requests. This removes ambiguity and improves performance by only requiring writing to the SecurityContextRepository (i.e. HttpSession) when it is necessary.

        *   If you are explicitly opting into Spring Security 6’s new defaults, the following configuration can be removed to accept the Spring Security 6 defaults.

        *   ```Explicit Saving of SecurityContext```
        ```
        public SecurityFilterChain filterChain(HttpSecurit http){
            http
                // ...
                .securityContext((securityContext) -> securityContext
                    .requireExplicitSave(true)
                );
            return http.build();
        }
        ```

        *   Upon using the configuration, it is important that any code that sets the SecurityContextHolder with a SecurityContext also saves the SecurityContext to the SecurityContextRepository if it should be persisted between requests.

        ``` 
        SecurityContextHolder.setContext(securityContext);  

        ```
        *   Setting SecurityContextHolder with SecurityContextHolderFilter
        ```
        SecurityContextHolder.setContext(securityContext);
        securityContextRepository.saveContext(securityContext,httpServletRequest, httpServletResponse);
        ```
        
    *   Optimize Querying of RequestCache

        *   In Spring Security 5, the default behavior is to query the saved request on every request. This means that in a typical setup, that in order to use the RequestCache the HttpSession is queried on every request.

        *   In Spring Security 6, the default is that RequestCache will only be queried for a cached request if the HTTP parameter continue is defined. This allows Spring Security to avoid unnecessarily reading the HttpSession with the RequestCache.

        *   In Spring Security 5 the default is to use HttpSessionRequestCache which will be queried for a cached request on every request. If you are not overriding the defaults (i.e. using NullRequestCache), then the following configuration can be used to explicitly opt into the Spring Security 6 behavior in Spring Security 5.8:   

        
          *   RequestCache Only Checks for Saved Requests if continue Parameter Present

          ```
          @Bean
            DefaultSecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
	        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
	        requestCache.setMatchingRequestParameterName("continue");
	        http
		    // ...
		    .requestCache((cache) -> cache
			.requestCache(requestCache)
		    );
	        return http.build();
            }

            ```
*
        