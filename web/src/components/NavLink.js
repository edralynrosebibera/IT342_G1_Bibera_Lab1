import { NavLink as RouterNavLink } from "react-router-dom"
import { forwardRef } from "react"

function cn(...classes) {
  return classes.filter(Boolean).join(" ")
}

const NavLink = forwardRef(
  ({ className, activeClassName, pendingClassName, to, ...props }, ref) => {
    return (
      <RouterNavLink
        ref={ref}
        to={to}
        className={({ isActive, isPending }) =>
          cn(
            className,
            isActive && activeClassName,
            isPending && pendingClassName
          )
        }
        {...props}
      />
    )
  }
)

NavLink.displayName = "NavLink"

export default NavLink
