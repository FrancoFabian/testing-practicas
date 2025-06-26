Feature: Gestion de productos
  Scenario: Crear producto exitosamente
    Given el sistema esta disponible
    When creo un producto con el nombre "Sombrero"
    Then el producto "Sombrero" debe estar creado