# Shayna Bahia - Personal Project

## Online Shopping Cart

>My personal project for CPSC 210 is an **online shopping cart**. This application will be similar to 
any online shopping website where you can order items online. Users will be able to add and remove 
items of their choice to the cart, and apply or remove discounts to items in their cart. There will
also be an option to add items that are out of stock onto a wishlist. Users should be able to reorder
their cart, as well as see the total price of all the items in the cart. 

>Anyone who likes to shop, or needs to order something online that is not available in store
can use this application. In fact, anyone will be able to try out this online shopping cart. 
I chose to create a shopping cart because now more than ever, online shopping has become 
a new normal for many people around the world. I use online shopping services all the time, and 
thought that it would be interesting to design my own. I am also interested in seeing if there are
any cool features that I could add to my online shopping cart that is unique an authentic. 

### User stories:
> - As a user, I want to be able to *add* arbitrary items to the cart
> - As a user, I want to be able to *add* arbitrary items to my wish list 
> - As a user, I want to be able to *calculate* my total of all items in the cart
> - As a user, I want to be able to *apply* discounts to an item(s) in the cart
> - As a user, I want to be able to *see* my entire cart, or a list of all items in the cart
> - As a user, I want to be able to **see** my entire wishlist
> - As a user, I want to be able to *save* my entire program
> - As a user, I want to be able to be able to load my shopping cart/wishlist from file (if I so choose)

### Instructions For Grader: 

> - You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the 
    "add to cart" button on the cart panel, or on the "add to list" button on the wishlist panel
> - You can generate the second required action related to the user story "adding multiple Xs to a Y" by: clicking on 
    the "display cart" or "display wishlist" button. You can also click on the "remove" button to remove an item in the
    cart. REMINDER: user should save the cart before the would like to remove an item  or add an item. To filter all the
    X's in Y, you can click on the filter button. 
> - You can locate my visual component by clearing the cart by pressing the "clear cart" button. Once pressed, the 
    user will be prompted to confirm if they want to clear the cart. Then, a picture will appear. To get rid of the 
    picture, simply press the "ok" button. 
> - You can save the state of my application by clicking the "save" button.
> - You can reload the state of my application by clicking the "load" button. WARNING: user should save cart before 
    loading the cart! 

### Phase 4 Task 2: 
goodbye!

all events:

Fri Dec 01 12:16:10 PST 2023

item added to cart:  shirt

Fri Dec 01 12:16:45 PST 2023

item added to cart: pants 

Fri Dec 01 12:16:56 PST 2023

item added to cart: socks

Fri Dec 01 12:17:08 PST 2023

item added to cart: hoodie

Fri Dec 01 12:18:10 PST 2023

item added to cart: sweatpants

Fri Dec 01 12:18:22 PST 2023

Item removed from cart:  shirt

Fri Dec 01 12:18:30 PST 2023

cart cleared!


### Phase 4 Task 3: 

> In order to refactor my project if I had more time, I would lessen the duplication of code between my ShoppingCart 
class and my WishList class. These classes are essentially the same, both contains a list of Items where you can add an 
item, remove an item etc. In order to do this I would create an abstract class and make that the super class. 
From there, I would add methods such as add(), remove() and clear() so that ShoppingCart and WishList could each have 
their own versions of these methods to implement. This would lessen the amount of duplication in the code, and it would 
be easier to read. 
> UML DIAGRAM FOUND UNDER DATA -> IMAGES

  