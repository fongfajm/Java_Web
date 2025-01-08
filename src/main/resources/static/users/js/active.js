document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname; // Lấy đường dẫn hiện tại
    const menuItems = document.querySelectorAll(".header__menu ul li a");

    menuItems.forEach(item => {
        if (item.getAttribute("href") === currentPath) {
            item.parentElement.classList.add("active");
        }
    });
});
