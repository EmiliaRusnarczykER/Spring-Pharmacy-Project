'use client'
import React from "react";
import { Navbar, NavbarBrand, NavbarContent, NavbarItem } from "@nextui-org/navbar";
import { Link } from "@nextui-org/link";
import { Button } from "@nextui-org/button";
import { Dropdown, DropdownTrigger, DropdownMenu, DropdownItem } from "@nextui-org/dropdown";
import { ChevronDown } from "./lib/icons";
import { useCookies } from "next-client-cookies";

export default function App ({ categories }: any) {
  const cookies = useCookies()
  const token = cookies.get('token')

  const icons = {
    chevron: <ChevronDown fill="currentColor" size={16} />
  };
  const AcmeLogo = () => (
    <svg fill="none" height="36" viewBox="0 0 32 32" width="36">
      <path
        clipRule="evenodd"
        d="M17.6482 10.1305L15.8785 7.02583L7.02979 22.5499H10.5278L17.6482 10.1305ZM19.8798 14.0457L18.11 17.1983L19.394 19.4511H16.8453L15.1056 22.5499H24.7272L19.8798 14.0457Z"
        fill="currentColor"
        fillRule="evenodd"
      />
    </svg>
  );
  function signOut (e: any) {
    cookies.remove('token')
  }

  return (
    <Navbar>
      <NavbarBrand>
        <AcmeLogo />
        <p className="font-bold text-inherit">EMILIA</p>
      </NavbarBrand>
      <NavbarContent className="hidden sm:flex gap-4" justify="center">
        <NavbarItem isActive>

          <Link href="/" className="text-inherit" color="foreground">
            Home
          </Link>

        </NavbarItem>
        <NavbarItem>
          <Link color="foreground" href="/medicine">
            CRUD Medicine
          </Link>
        </NavbarItem>
      </NavbarContent>
      {
        token ?
          <NavbarContent justify="end">
            <NavbarItem>
              <Button as={Link} onClick={signOut} color="primary" href="#" variant="flat">
                Sign Out
              </Button>
            </NavbarItem>
          </NavbarContent>
          :
          <NavbarContent justify="end">
            <NavbarItem className="hidden lg:flex">
              <Link href="/login">Login</Link>
            </NavbarItem>
            <NavbarItem>
              <Button as={Link} color="primary" href="/register" variant="flat">
                Sign Up
              </Button>
            </NavbarItem>
          </NavbarContent>
      }
    </Navbar>
  );
}
