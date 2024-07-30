import { redirect } from 'next/navigation'
import Form from '../components/Form'
import MedicineCard from '../components/Card'
import MedicineCardList from '../components/MedicineCardList'


export default async function Page () {
  async function getData () {
    const res = await fetch('http://localhost:8080/api/medicines/', { cache: 'no-store' })
    if (!res.ok) {
      redirect('http://localhost:8080/login')
    }
    return res.json()
  }
  const data = await getData()

  return (
    <section>
      <MedicineCardList data={data} />
    </section>
  );
}
