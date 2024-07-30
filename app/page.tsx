import Image from "next/image";
import MedicineCard from "./components/Card";
import { redirect } from "next/navigation";

export default async function Home () {
  async function getData () {
    const res = await fetch('http://localhost:8080/api/medicines/', { cache: 'no-store' })
    //if (!res.ok) {
    //  redirect('http://localhost:8080/auth/login')
    //}
    return res.json()
  }

  const data = await getData()

  return (
    <section>
      <MedicineCard data={data} />
    </section>
  );
}
